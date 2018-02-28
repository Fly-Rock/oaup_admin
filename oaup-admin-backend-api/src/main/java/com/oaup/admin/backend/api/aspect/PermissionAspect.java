package com.oaup.admin.backend.api.aspect;

import com.hujiang.basic.framework.core.config.BaseProperties;
import com.hujiang.basic.framework.core.exception.AppException;
import com.hujiang.basic.framework.rest.model.BaseUserRequest;
import com.oaup.admin.backend.api.annotation.Permisson;
import com.oaup.admin.backend.service.services.Permission.SystemPermissionService;
import com.oaup.admin.backend.support.model.po.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Order(-200)
@Slf4j
public class PermissionAspect {

    @Autowired
    SystemPermissionService systemPermissionService;

    private Header[] headers = new Header[4];


    @Pointcut("execution(public * com.oaup.admin.backend.api.controller.*Controller.*(..)) " +
            "&& @annotation(com.oaup.admin.backend.api.annotation.Permisson)")
    public void permission() {

    }

    @Around(" @annotation(permission)")
    public Object handler(ProceedingJoinPoint pjp, Permisson permission) throws Throwable {

        Object retVal = null;  //连接点方法返回值

        BaseUserRequest request = null;
        HttpServletRequest servletRequest = null;

        //获取执行方法的参数
        Object[] args = pjp.getArgs();
        //从参数列表中获取参数对象
        for (Object obj : args) {//查看参数值
            if (obj instanceof BaseUserRequest) {
                request = (BaseUserRequest) obj;
            }

            if (obj instanceof HttpServletRequest) {
                servletRequest = (HttpServletRequest) obj;
            }
        }


        if (servletRequest == null) {
            servletRequest = ContextHelper.getRequest();
        }

        try {
            verifyRequest(servletRequest, request);

            String main = permission.main();
            String sub = permission.sub();


            if (!systemPermissionService.checkSuperAdmin((Integer) request.getUserId()) &&
                    !systemPermissionService.checkSystemPermission((Integer) request.getUserId(),
                            main, sub)) {
                HttpServletResponse response = ContextHelper.getResponse();


                response.sendRedirect(servletRequest.getContextPath() + "/backend/tips/error");

                //throw new AppException(-40300, "没有权限");
            }

        } catch (AppException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("VerifyAspect:%s\r\n%s\r\n%s", ex.getClass().getName(), ex.getMessage(), ex.getStackTrace());
        }

        //if (servletRequest == null || request == null)
        //throw new AppException(-40001, "参数错误~~~~");

        retVal = pjp.proceed();

        return retVal;
    }

    private boolean verifyRequest(HttpServletRequest request, BaseUserRequest userRequest) {

        boolean flag = false;
        UserInfo userInfo = null;

        if (!flag) {
            userInfo = verifyClubAuth(request);
            flag = flag || userInfo != null;
        }

        if (userInfo != null) {
            userRequest.setUserId(userInfo.getUserId());

            request.setAttribute("userid", userInfo.getUserId());
            request.setAttribute("username", userInfo.getUserName());

            String env = BaseProperties.getProperty("spring.profiles.active", String.class);
            String host = String.format("http://%sadmin.cctalk.com/admin/", env.replace("prod", ""));
            request.setAttribute("returnUrl", host);


        }

        if (!flag)
            throw new AppException(-40100, "认证错误`````");

        return flag;
    }

    private UserInfo verifyClubAuth(HttpServletRequest request) {
        try {
            return getUser(request);
        } catch (Exception ex) {
            return null;
        }
    }


    private UserInfo getUser(HttpServletRequest request) {
        String cookieName = BaseProperties.getProperty("auth.cookie.key", "ClubAuth_dev");
        Map<String, String> cookieValues = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieValues.put(cookie.getName(), cookie.getValue());
            }
        }
        if (!cookieValues.containsKey(cookieName)) return null;
        return _getUser(cookieValues.get(cookieName));
    }

    private UserInfo _getUser(String authCookie) {
        return null;
    }
}

package com.oaup.admin.backend.api.aspect;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ContextHelper {
    public static ServletRequestAttributes getAttributes() {
        RequestAttributes attributes = RequestContextHolder.currentRequestAttributes();
        return attributes == null ? null : (ServletRequestAttributes) attributes;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = getAttributes();
        return attributes == null ? null : attributes.getRequest();
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes attributes = getAttributes();
        return attributes == null ? null : attributes.getResponse();
    }

    public static HttpSession getSession() {
        HttpServletRequest request = getRequest();
        return request == null ? null : request.getSession();
    }

    public static String getHeader(String name) {
        HttpServletRequest request = getRequest();
        return request == null ? null : request.getHeader(name);
    }

    public static boolean setHeader(String name, String value) {
        HttpServletResponse response = getResponse();
        if (response != null) {
            response.setHeader(name, value);
            return true;
        }
        return false;
    }

    public static String getUserAgent() {
        HttpServletRequest request = getRequest();
        return request == null ? "" : request.getHeader("User-Agent");
    }

}

package com.oaup.admin.backend.api.controller;
import com.hujiang.basic.framework.rest.model.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping(value = "/backend/")
public class PassUserController {


    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView doLogin(HttpServletRequest req,Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/admin_login");
        return modelAndView;
    }

    @RequestMapping(value = "login_do", method = RequestMethod.GET)
    @ResponseBody
    public DataResult<Integer> doLoginOption() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return DataResult.ok(0,"",12);
    }

}

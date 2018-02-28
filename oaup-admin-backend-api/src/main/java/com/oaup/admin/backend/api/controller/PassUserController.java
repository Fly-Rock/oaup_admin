package com.oaup.admin.backend.api.controller;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PassUserController.class);

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView doLogin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

}

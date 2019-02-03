package com.hiking.controller;

import com.hiking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/" )
    public String index(ModelMap model, HttpServletRequest request) {


        Principal principal = request.getUserPrincipal();

        String username = principal.getName();

        int user_id = userService.getUserIdByName(username);
        model.addAttribute("message", "bienvenue au 'Randonneur' !!!");
        model.addAttribute("username", username);
        model.addAttribute("user_id", user_id);

        return "index";
    }

}

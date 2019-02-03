package com.hiking.controller;

import com.hiking.model.Hike;
import com.hiking.model.User;
import com.hiking.repository.HikeRepository;
import com.hiking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    private HikeRepository hikeRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/showProfil/{user_id}", method = RequestMethod.GET)
    public String showProfil(@PathVariable(value = "user_id") int user_id, ModelMap model, HttpServletRequest request)
    {

        Principal principal = request.getUserPrincipal();

        String username = principal.getName();

        int userIdAuth = userService.getUserIdByName(username);

        if(userIdAuth == user_id) {
            User user = userService.getUser(user_id);

            String name = user.getUsername();

            int nbrDoneHikes = userService.getNumberOfDoneHikes(user_id);

            int nbrHikeToDo = userService.getNumberOfHikesTodo(user_id);

            double nbrKmsWalked = userService.getNbrOfKmsWalked(user_id);

            model.addAttribute("nbrDoneHikes", nbrDoneHikes);
            model.addAttribute("nbrHikeToDo", nbrHikeToDo);
            model.addAttribute("nbrKmsWalked", nbrKmsWalked);

            model.addAttribute("user", user);
            model.addAttribute("name", name);

            return "showProfil";
        } else {
            return "403";
        }

    }


}

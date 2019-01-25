package com.object173.shop.controller;

import com.object173.shop.model.User;
import com.object173.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    private UserService userService;
    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService)
    {
        this.userService=userService;
    }

    @RequestMapping(value = "userPage", method = RequestMethod.GET)
    public String userPage(Model model)
    {
        User user = this.userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "userPage";
    }

    @RequestMapping(value = "/userPage/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user, Model model)
    {
        try {
            if(!this.userService.updateUser(user))
            {
                model.addAttribute("error","error validation");
            }
        }
        catch(Exception ex)
        {
            model.addAttribute("error",ex.toString());
        }
        return "userPage";
    }
}

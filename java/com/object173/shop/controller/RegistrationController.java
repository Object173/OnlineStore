package com.object173.shop.controller;

import com.object173.shop.model.User;
import com.object173.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {
    private UserService userService;
    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService)
    {
        this.userService=userService;
    }

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String registration(Model model)
    {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/registrationUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, Model model)
    {

        try {
            if(!this.userService.addUser(user))
            {
                model.addAttribute("error","error validation");
                return "registration";
            }
        }
        catch(Exception ex)
        {
            model.addAttribute("error",ex.toString());
            return "registration";
        }
        return "redirect:/j_spring_security_check";
    }
}

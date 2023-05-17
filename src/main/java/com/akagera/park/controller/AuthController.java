package com.akagera.park.controller;

import com.akagera.park.model.User;
import com.akagera.park.services.FormService;
import com.akagera.park.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AuthController {
    @Autowired
    UserService userService;
    public AuthController(FormService formService) {
        this.formService = formService;
    }
    private final FormService formService;

    @RequestMapping(value = {"/admin/dashboard"}, method = RequestMethod.GET)
    public String adminHome() {
//        model.addAttribute("data",formService.getAllForms());
        return "admin/dashboard";
    }

    @GetMapping("/table")
    public String getDashboard(Model model){
        model.addAttribute("students" , formService.getAllForms());
        return "/table";
    }
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home(){
        return "homepage";
    }
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(){
        return "auth/login";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String registerUser(Model model, User user, BindingResult bindingResult){
        System.out.println(
                "helllo"
        );
        if(bindingResult.hasErrors()){
            model.addAttribute("successMessage", "User registered successfully!");
            model.addAttribute("bindingResult", bindingResult);
            return "auth/register";
        }
        List<Object> userPresentObj = userService.isUserPresent(user);
        if((Boolean) userPresentObj.get(0)){
            model.addAttribute("successMessage", userPresentObj.get(1));
            return "auth/register";
        }

        userService.saveUser(user);
        model.addAttribute("successMessage", "User registered successfully!");

        return "auth/login";

    }


}


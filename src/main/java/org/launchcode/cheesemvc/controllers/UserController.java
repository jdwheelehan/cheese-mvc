package org.launchcode.cheesemvc.controllers;


import org.launchcode.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "")
        public String displayIndex(Model model, @RequestParam String name){
        model.addAttribute("name", name);

        return "user/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddUserForm(Model model){
        model.addAttribute("title", "Add User");
        model.addAttribute(new User());

        return "user/add";
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddUserForm(@ModelAttribute @Valid User user, Errors errors, Model model, String verify){

        User newUser = user;
        String verified = verify;

        if (errors.hasErrors()){
            model.addAttribute("title", "Add User");
            model.addAttribute(user);
            return "user/add";
        }
        if (verified.equals(newUser.getPassword())){
            model.addAttribute("name","${newUser.getUsername}");
            return "redirect:";
        }
            model.addAttribute("verify", "Password and Verify did'nt match");
            model.addAttribute("name", "${newUser.getUsername}");
            model.addAttribute("email", "${newUser.getEmail}");
            user.setPassword("");
            return "redirect:user/add";

        }



    }


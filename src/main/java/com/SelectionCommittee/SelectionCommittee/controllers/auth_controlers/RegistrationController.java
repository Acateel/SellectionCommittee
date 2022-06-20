package com.SelectionCommittee.SelectionCommittee.controllers.auth_controlers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String getRegistrationForm(Model model){
        return "auth/register";
    }
}

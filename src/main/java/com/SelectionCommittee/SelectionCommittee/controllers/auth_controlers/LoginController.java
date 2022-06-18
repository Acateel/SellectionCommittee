package com.SelectionCommittee.SelectionCommittee.controllers.auth_controlers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model){
        return "auth/login";
    }
}
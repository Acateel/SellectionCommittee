package com.SelectionCommittee.SelectionCommittee.controllers.auth_controlers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class LoginController {

    @GetMapping("/login")
    public String login(Model model){
        log.info("Show login page");
        return "auth/login";
    }
}

package com.SelectionCommittee.SelectionCommittee.controllers.auth_controlers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Login controller - show login page
 */
@Controller
@Log4j2
public class LoginController {

    @GetMapping("/login")
    public String login(){
        log.info("Show login page");
        return "auth/login";
    }
}

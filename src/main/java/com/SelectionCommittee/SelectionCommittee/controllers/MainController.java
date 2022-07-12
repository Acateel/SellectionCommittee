package com.SelectionCommittee.SelectionCommittee.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Main controller is responsible for displaying the main page
 */
@Controller
@Log4j2
public class MainController {
    /**
     * Show main page
     * @return name of template for thymeleaf
     */
    @GetMapping("/")
    public String main() {
        log.info("Show main page");
        return "main";
    }
}

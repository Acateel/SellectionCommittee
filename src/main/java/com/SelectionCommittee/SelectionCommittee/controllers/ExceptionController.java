package com.SelectionCommittee.SelectionCommittee.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    private static final String ERROR_PAGE = "error";

    @GetMapping("/error")
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public String forbidden(Model model){
        model.addAttribute("forbidden", true);
        return ERROR_PAGE;
    }
}

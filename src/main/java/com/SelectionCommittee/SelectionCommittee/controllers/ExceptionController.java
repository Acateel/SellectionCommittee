package com.SelectionCommittee.SelectionCommittee.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Exception Controller is responsible for displaying the error page
 */
@ControllerAdvice
@Log4j2
public class ExceptionController {

    private static final String ERROR_PAGE = "error";

    /**
     * Add error status into model and show error page
     * @param model for add status attribute
     * @param request for get error status code
     * @return model name of error page for thymeleaf
     */
    @GetMapping("/error")
    public String showError(Model model, HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        log.error("Error happened, status: {}", status);
        model.addAttribute("status", status);
        return ERROR_PAGE;
    }
}

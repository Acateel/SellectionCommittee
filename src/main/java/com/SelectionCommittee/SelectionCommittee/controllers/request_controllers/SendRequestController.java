package com.SelectionCommittee.SelectionCommittee.controllers.request_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SendRequestController {

    @GetMapping("/send_request")
    public String getSendRequestForm(Model model){
        return "send_request";
    }
}

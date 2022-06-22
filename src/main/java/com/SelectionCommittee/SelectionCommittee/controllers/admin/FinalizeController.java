package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import com.SelectionCommittee.SelectionCommittee.finalizers.Finalizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FinalizeController {

    @Autowired
    protected Finalizer finalizer;

    @GetMapping("/finalize")
    public String finalizeRequests(Model model){
        finalizer.finalizeRequests();
        model.addAttribute("finalize_complete", true);
        return "main";
    }
}

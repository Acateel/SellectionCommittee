package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import com.SelectionCommittee.SelectionCommittee.finalizers.Finalizer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Finalize controller - finalizes requests
 */

@Controller
@Log4j2
public class FinalizeController {

    @Autowired
    protected Finalizer finalizer;

    /**
     * Finalize requests
     *
     * @param model for add attribute
     * @return model name of main page
     */
    @GetMapping("/finalize")
    public String finalizeRequests(Model model) {
        log.info("finalize requests");
        finalizer.finalizeRequests();
        model.addAttribute("finalize_complete", true);
        return "main";
    }
}

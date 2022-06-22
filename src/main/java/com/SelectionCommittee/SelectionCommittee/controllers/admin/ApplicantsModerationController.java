package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import com.SelectionCommittee.SelectionCommittee.repositories.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicantsModerationController {
    @Autowired
    protected ApplicantRepository applicantRepository;

    @GetMapping("/applicants")
    public String getApplicants(Model model) {
        var applicants = applicantRepository.findAll();
        model.addAttribute("applicants", applicants);
        return "admin/applicants";
    }
}

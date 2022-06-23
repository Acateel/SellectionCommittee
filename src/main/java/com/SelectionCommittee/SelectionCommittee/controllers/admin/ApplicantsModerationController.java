package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import com.SelectionCommittee.SelectionCommittee.models.ApplicantEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/block_applicant")
    public String blockApplicant(@RequestParam int applicantId, Model model) {
        setBlock(applicantId, true);
        return "redirect:/applicants";
    }

    @GetMapping("/deblock_applicant")
    public String deblockApplicant(@RequestParam int applicantId, Model model) {
        setBlock(applicantId, false);
        return "redirect:/applicants";
    }

    private void setBlock(int applicantId, boolean block) {
        ApplicantEntity applicant = applicantRepository.findById(Long.valueOf(applicantId)).get();
        applicant.setBlock((byte)(block ? 1 : 0));
        applicantRepository.save(applicant);
    }
}
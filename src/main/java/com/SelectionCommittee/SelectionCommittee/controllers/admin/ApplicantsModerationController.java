package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import com.SelectionCommittee.SelectionCommittee.models.ApplicantEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.ApplicantRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;

/**
 *  Applicants moderation controller - show applicants and set block for applicant
 */
@Controller
@Log4j2
public class ApplicantsModerationController {
    @Autowired
    protected ApplicantRepository applicantRepository;
    private static final int PAGE_SIZE = 10;

    @GetMapping("/applicants")
    public String getApplicants(@RequestParam(required = false, defaultValue = "0") int page ,Model model) {
        log.info("Show applicants");
        page = getPage(page);
        model.addAttribute("page", page);

        var applicants = applicantRepository.findAll(PageRequest.of(page, PAGE_SIZE));
        model.addAttribute("applicants", applicants);
        return "admin/applicants";
    }

    @GetMapping("/block_applicant")
    public String blockApplicant(@RequestParam int applicantId, Model model) {
        log.info("Block applicant, id={}", applicantId);
        setBlock(applicantId, true);
        return "redirect:/applicants";
    }

    @GetMapping("/deblock_applicant")
    public String deblockApplicant(@RequestParam int applicantId, Model model) {
        log.info("Reset block applicant, id={}", applicantId);
        setBlock(applicantId, false);
        return "redirect:/applicants";
    }

    private void setBlock(int applicantId, boolean block) {
        var optional = applicantRepository.findById((long) applicantId);
        if (optional.isEmpty()) {
            log.error("Applicant not found, Id={}", applicantId);
            throw new NoSuchElementException("Applicant not found, Id=" + applicantId);
        }
        ApplicantEntity applicant = optional.get();
        applicant.setBlock((byte) (block ? 1 : 0));
        applicantRepository.save(applicant);
    }

    private int getPage(int page) {
        if (page < 0) {
            page = 0;
        } else {
            int counts = applicantRepository.countAllBy();
            int showedCounts = page * PAGE_SIZE;
            if (showedCounts > counts) {
                page--;
            }
        }
        return page;
    }
}

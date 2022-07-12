package com.SelectionCommittee.SelectionCommittee.controllers;

import com.SelectionCommittee.SelectionCommittee.models.ApplicantEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.ApplicantRepository;
import com.SelectionCommittee.SelectionCommittee.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.NoSuchElementException;

/**
 * Applicant Controller - is responsible for displaying the applicant info page and upload attestation scan file
 */
@Controller
@Log4j2
public class ApplicantController {
    @Autowired
    ApplicantRepository applicantRepository;
    @Autowired
    UserRepository userRepository;

    private static final String APPLICANT_KEY = "applicant";

    /**
     * Show applicant information
     * @param model for add applicant attribute
     * @param principal for get applicant id from user entity
     * @return model name of applicant info page
     */
    @GetMapping("/applicant")
    public String getUserInfo(Model model, Principal principal) {
        log.info("Show applicant info");
        long applicantId = getApplicantId(principal.getName());
        ApplicantEntity applicant = getApplicant(applicantId);
        model.addAttribute(APPLICANT_KEY, applicant);
        return APPLICANT_KEY;
    }

    /**
     * Upload attestation scan file and show file not upload if error happened
     * @param file attestation scan
     * @param model for add attributes
     * @param principal for get user for get applicant id
     * @return model name of applicant info page
     */
    @PostMapping("/applicant")
    public String uploadAttestation(@RequestParam("file") MultipartFile file, Model model, Principal principal) {
        long applicantId = getApplicantId(principal.getName());
        ApplicantEntity applicant = getApplicant(applicantId);
        try {
            applicant.setAttestation(file.getBytes());
            applicantRepository.save(applicant);
            model.addAttribute("file_upload", true);
        } catch (IOException exception) {
            log.error("File not upload");
            model.addAttribute("file_not_upload", true);
        }
        model.addAttribute(APPLICANT_KEY, applicant);
        return APPLICANT_KEY;
    }

    /**
     * Get Applicant by applicant id
     * @param applicantId applicant id from user
     * @return applicant entity from DB
     */
    private ApplicantEntity getApplicant(long applicantId) {
        var applicant = applicantRepository.findById(applicantId);
        if (applicant.isEmpty()) {
            log.error("Applicant not found, Id={}", applicantId);
            throw new NoSuchElementException("Applicant not found, Id=" + applicantId);
        }
        return applicant.get();
    }

    /**
     * Get applicant id from user by username or error if applicant not found
     * @param login username
     * @return applicant with applicant
     */
    private long getApplicantId(String login) {
        var user = userRepository.findByLogin(login);
        if (user.isEmpty()) {
            log.error("User not found, name={}", login);
            throw new NoSuchElementException("User not found, name=" + login);
        }
        return user.get().getApplicantId();
    }

}

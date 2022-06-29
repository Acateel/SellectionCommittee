package com.SelectionCommittee.SelectionCommittee.controllers.request_controllers;

import com.SelectionCommittee.SelectionCommittee.models.ApplicantEntity;
import com.SelectionCommittee.SelectionCommittee.models.RequestEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.ApplicantRepository;
import com.SelectionCommittee.SelectionCommittee.repositories.RequestRepository;
import com.SelectionCommittee.SelectionCommittee.repositories.UserRepository;
import com.SelectionCommittee.SelectionCommittee.validators.RequestValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.Time;
import java.util.Date;
import java.util.NoSuchElementException;

/**
 * Send request controller - show send request page, get information by form and after validation add request to DB
 */
@Controller
@Log4j2
public class SendRequestController {

    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected ApplicantRepository applicantRepository;
    @Autowired
    protected RequestRepository requestRepository;

    private int facultyId;

    private static final String SEND_REQUEST_PAGE = "send_request";

    @GetMapping("/send_request")
    public String getSendRequestForm(@RequestParam int facultyId, Model model) {
        log.info("show send request form");
        this.facultyId = facultyId;
        return SEND_REQUEST_PAGE;
    }

    @PostMapping("/send_request")
    public String sendRequestIntoDb(@RequestParam(name = "main_subject") int mainSubject,
                                    @RequestParam(name = "second_subject") int secondSubject,
                                    @RequestParam(name = "sub_subject") int subSubject,
                                    @RequestParam(name = "average_attestation_score") double attestationScore,
                                    Model model,
                                    Principal principal) {
        log.info("Send request, Input: main={}, second={}, sub={}, average={}", mainSubject, secondSubject, subSubject, attestationScore);
        RequestEntity request = getRequest(mainSubject, secondSubject, subSubject, attestationScore, principal.getName());
        addParamsIntoModel(mainSubject, secondSubject, subSubject, attestationScore, model);

        if (RequestValidator.checkRequest(request, model)) {
            boolean checkedBlock = checkApplicantBlock(request.getApplicantId(), model);
            boolean checkedUnique = checkRequestUnique(request, model);
            if (checkedBlock || checkedUnique) {
                return SEND_REQUEST_PAGE;
            } else {
                requestRepository.save(request);
                log.info("Save request in DB");
                model.addAttribute("request_send_complete", true);
                return "main";
            }
        }
        log.warn("Did not passed request validator");
        return SEND_REQUEST_PAGE;
    }

    private void addParamsIntoModel(int mainSubject, int secondSubject, int subSubject, double attestationScore, Model model) {
        model.addAttribute("main_subject", mainSubject);
        model.addAttribute("second_subject", secondSubject);
        model.addAttribute("sub_subject", subSubject);
        model.addAttribute("average_attestation_score", attestationScore);
    }

    private RequestEntity getRequest(int mainSubject, int secondSubject, int subSubject, double attestationScore, String username) {
        RequestEntity request = new RequestEntity();
        request.setId(0L);
        request.setStatus("not processed");
        request.setFacultiesId(facultyId);
        request.setApplicantId(getApplicantId(username));
        request.setMainSubject(mainSubject);
        request.setSecondSubject(secondSubject);
        request.setSubSubject(subSubject);
        request.setRatingScore(0);
        request.setAverageAttestationScore(attestationScore);
        request.setPublishTime(new Time(new Date().getTime()));

        return request;
    }

    private Long getApplicantId(String username) {
        var optional = userRepository.findByLogin(username);
        if (optional.isEmpty()) {
            log.error("Applicant not found, Email={}", username);
            throw new NoSuchElementException("Applicant not found, Email=" + username);
        }
        return optional.get().getApplicantId();
    }

    private boolean checkApplicantBlock(Long applicantId, Model model) {
        var optional = applicantRepository.findById(applicantId);
        if(optional.isEmpty()){
            log.error("Applicant not found, Id={}", applicantId);
            throw new NoSuchElementException("Applicant not found, id="+applicantId);
        }
        ApplicantEntity applicant = optional.get();
        if (applicant.getBlock() == 1) {
            log.warn("Check applicant block false, applicant id = {}", applicant.getId());
            model.addAttribute("applicant_block", true);
            return true;
        }
        return false;
    }

    private boolean checkRequestUnique(RequestEntity request, Model model) {
        RequestEntity requestInDB = requestRepository.findByFacultiesIdAndApplicantId(request.getFacultiesId(), request.getApplicantId());
        if (requestInDB != null) {
            log.warn("Check request unique false, request id = {}", requestInDB.getId());
            model.addAttribute("request_not_unique", true);
            return true;
        }
        return false;
    }
}

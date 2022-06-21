package com.SelectionCommittee.SelectionCommittee.controllers.request_controllers;

import com.SelectionCommittee.SelectionCommittee.models.ApplicantEntity;
import com.SelectionCommittee.SelectionCommittee.models.RequestEntity;
import com.SelectionCommittee.SelectionCommittee.models.UserEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.ApplicantRepository;
import com.SelectionCommittee.SelectionCommittee.repositories.RequestRepository;
import com.SelectionCommittee.SelectionCommittee.repositories.UserRepository;
import com.SelectionCommittee.SelectionCommittee.validators.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.Time;
import java.util.Date;

@Controller
public class SendRequestController {

    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected ApplicantRepository applicantRepository;
    @Autowired
    protected RequestRepository requestRepository;

    private int facultyId;

    @GetMapping("/send_request")
    public String getSendRequestForm(@RequestParam int facultyId, Model model) {
        this.facultyId = facultyId;
        return "send_request";
    }

    @PostMapping("/send_request")
    public String sendRequestIntoDb(@RequestParam(name = "main_subject") int mainSubject,
                                    @RequestParam(name = "second_subject") int secondSubject,
                                    @RequestParam(name = "sub_subject") int subSubject,
                                    @RequestParam(name = "average_attestation_score") double attestationScore,
                                    Model model,
                                    Principal principal) {
        RequestEntity request = getRequest(mainSubject, secondSubject, subSubject, attestationScore, principal.getName());
        addParamsIntoModel(mainSubject, secondSubject, subSubject, attestationScore, model);

        if (RequestValidator.checkRequest(request, model)) {
            boolean checkedBlock = checkApplicantBlock(request.getApplicantId(), model);
            boolean checkedUnique = checkRequestUnique(request, model);
            if (checkedBlock || checkedUnique) {
                return "send_request";
            } else {
                requestRepository.save(request);
                model.addAttribute("request_send_complete", true);
                return "main";
            }
        }

        return "send_request";
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
        request.setApplicantId(userRepository.findByLogin(username).get().getApplicantId());
        request.setMainSubject(mainSubject);
        request.setSecondSubject(secondSubject);
        request.setSubSubject(subSubject);
        request.setRatingScore(0);
        request.setAverageAttestationScore(attestationScore);
        request.setPublishTime(new Time(new Date().getTime()));

        return request;
    }

    private boolean checkApplicantBlock(Long applicantId, Model model) {
        ApplicantEntity applicant = applicantRepository.findById(applicantId).get();
        if (applicant.getBlock() == 1) {
            model.addAttribute("applicant_block", true);
            return true;
        }
        return false;
    }

    private boolean checkRequestUnique(RequestEntity request, Model model) {
        RequestEntity requestInDB = requestRepository.findByFacultiesIdAndApplicantId(request.getFacultiesId(), request.getApplicantId());
        if (requestInDB != null) {
            model.addAttribute("request_not_unique", true);
            return true;
        }
        return false;
    }
}

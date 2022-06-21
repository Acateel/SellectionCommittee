package com.SelectionCommittee.SelectionCommittee.controllers.request_controllers;

import com.SelectionCommittee.SelectionCommittee.models.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.util.Date;

@Controller
public class SendRequestController {

    private int facultyId;
    @GetMapping("/send_request")
    public String getSendRequestForm(@RequestParam int facultyId, Model model){
        this.facultyId = facultyId;
        return "send_request";
    }

    @PostMapping("/send_request")
    public String sendRequestIntoDb(@RequestParam(name = "main_subject") int mainSubject,
                                    @RequestParam(name = "second_subject") int secondSubject,
                                    @RequestParam(name = "sub_subject") int subSubject,
                                    @RequestParam(name = "average_attestation_score") double attestationScore,
                                    Model model){
        RequestEntity request = getRequest(mainSubject, secondSubject, subSubject, attestationScore);
        addParamsIntoModel(mainSubject, secondSubject, subSubject, attestationScore, model);

        return "send_request";
    }

    private void addParamsIntoModel(int mainSubject, int secondSubject, int subSubject, double attestationScore, Model model) {
        model.addAttribute("main_subject", mainSubject);
        model.addAttribute("second_subject", secondSubject);
        model.addAttribute("sub_subject", subSubject);
        model.addAttribute("average_attestation_score", attestationScore);
    }

    private RequestEntity getRequest(int mainSubject, int secondSubject, int subSubject, double attestationScore) {
        RequestEntity request = new RequestEntity();
        request.setId(0L);
        request.setStatus("not processed");
        request.setFacultiesId(facultyId);
        //        request.setApplicantId(); add from security
        request.setMainSubject(mainSubject);
        request.setSecondSubject(secondSubject);
        request.setSubSubject(subSubject);
        request.setRatingScore(0);
        request.setAverageAttestationScore(attestationScore);
        request.setPublishTime(new Time(new Date().getTime()));

        return request;
    }
}

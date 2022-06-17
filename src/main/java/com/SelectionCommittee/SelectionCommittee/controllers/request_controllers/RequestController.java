package com.SelectionCommittee.SelectionCommittee.controllers.request_controllers;

import com.SelectionCommittee.SelectionCommittee.models.FacultiesEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.FacultiesRepository;
import com.SelectionCommittee.SelectionCommittee.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestController {

    @Autowired
    FacultiesRepository facultiesRepository;
    @Autowired
    RequestRepository requestRepository;

    private static final int PAGE_SIZE = 7;

    @GetMapping("/request")
    public String showRequest(@RequestParam int facultyId, @RequestParam(required = false, defaultValue = "0") int page, Model model) {
        model.addAttribute("page", page);

        FacultiesEntity faculty = facultiesRepository.findById((long) facultyId).get();
        model.addAttribute("faculty", faculty);

        var requests = requestRepository.findAllByFacultiesIdOrderByRatingScoreDesc(facultyId, PageRequest.of(page,PAGE_SIZE));
        model.addAttribute("requests", requests);
        return "request";
    }
}

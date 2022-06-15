package com.SelectionCommittee.SelectionCommittee.controllers;

import com.SelectionCommittee.SelectionCommittee.models.FacultiesEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.FacultiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacultiesController {
    @Autowired
    private FacultiesRepository facultiesRepository;

    @GetMapping("/faculties")
    public String main(Model model) {
        Iterable<FacultiesEntity> faculties = facultiesRepository.findAll();
        model.addAttribute("faculties", faculties);
        return "faculties";
    }
}

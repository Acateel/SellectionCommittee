package com.SelectionCommittee.SelectionCommittee.controllers;

import com.SelectionCommittee.SelectionCommittee.models.FacultiesEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.FacultiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FacultiesController {
    @Autowired
    protected FacultiesRepository facultiesRepository;
    protected static final String FACULTY_PAGE = "faculties";
    protected static final String FACULTIES_ATTRIBUTE_NAME = "faculties";

    @GetMapping("/faculties")
    public String showFacultiesByOrder(@RequestParam(required = false, defaultValue = "byId") String order, Model model){
        Iterable<FacultiesEntity> faculties = getFacultiesByOrder(order);
        model.addAttribute(FACULTIES_ATTRIBUTE_NAME, faculties);
        return FACULTY_PAGE;
    }

    private Iterable<FacultiesEntity> getFacultiesByOrder(String order) {
        return switch (order) {
            case "byName" -> facultiesRepository.findAllByOrderByFacultyNameAsc();
            case "byNameRevers" -> facultiesRepository.findAllByOrderByFacultyNameDesc();
            case "byBudget" -> facultiesRepository.findAllByOrderByBudgetSeatsDesc();
            case "byTotal" -> facultiesRepository.findAllByOrderByTotalSeatsDesc();
            default -> facultiesRepository.findAll();
        };
    }
}

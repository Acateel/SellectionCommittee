package com.SelectionCommittee.SelectionCommittee.controllers.request_controllers;

import com.SelectionCommittee.SelectionCommittee.models.FacultiesEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.FacultiesRepository;
import com.SelectionCommittee.SelectionCommittee.repositories.RequestRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;

/**
 * Request controller - show request by faculty with pages
 */
@Controller
@Log4j2
public class RequestController {

    @Autowired
    FacultiesRepository facultiesRepository;
    @Autowired
    RequestRepository requestRepository;

    private static final int PAGE_SIZE = 7;

    @GetMapping("/request")
    public String showRequest(@RequestParam(required = false, defaultValue = "0") int facultyId,
                              @RequestParam(required = false, defaultValue = "0") int page,
                              Model model) {
        log.info("Show requests,Input: id={}, page={}", facultyId, page);
        if (facultyId == 0) {
            return "redirect:/faculties";
        }
        page = getPage(facultyId, page);
        model.addAttribute("page", page);

        FacultiesEntity faculty = getFaculties(facultyId);
        model.addAttribute("faculty", faculty);

        var requests = requestRepository.findAllByFacultiesIdOrderByRatingScoreDesc(facultyId, PageRequest.of(page, PAGE_SIZE));
        model.addAttribute("requests", requests);
        return "request";
    }

    private FacultiesEntity getFaculties(int facultyId) {
        var optional = facultiesRepository.findById((long) facultyId);
        if (optional.isEmpty()) {
            log.error("faculty with id={} not found", facultyId);
            throw new NoSuchElementException("Faculty not found, id=" + facultyId);
        }
        return optional.get();
    }

    private int getPage(int facultyId, int page) {
        if (page < 0) {
            page = 0;
        } else {
            int counts = requestRepository.countAllByFacultiesId(facultyId);
            int showedCounts = page * PAGE_SIZE;
            if (showedCounts > counts) {
                page--;
            }
        }
        return page;
    }
}

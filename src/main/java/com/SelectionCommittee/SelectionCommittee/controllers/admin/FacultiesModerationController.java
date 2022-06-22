package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import com.SelectionCommittee.SelectionCommittee.repositories.FacultiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FacultiesModerationController {

    @Autowired
    protected FacultiesRepository facultiesRepository;
    @GetMapping("/delete")
    public String deleteFaculty(@RequestParam int facultyId, Model model){
        facultiesRepository.deleteById((long) facultyId);
        return "redirect:/faculties";
    }
}

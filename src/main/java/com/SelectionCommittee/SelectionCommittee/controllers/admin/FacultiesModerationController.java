package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import com.SelectionCommittee.SelectionCommittee.models.FacultiesEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.FacultiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/add_faculty")
    public String getAddFacultyForm(Model model){
        return "admin/add_faculty";
    }

    @PostMapping("/add_faculty")
    public String addFaculty(@RequestParam(name = "faculty_name") String facultyName,
                             @RequestParam(name = "budget_seats") int budgetSeats,
                             @RequestParam(name = "total_seats") int totalSeats,
                             Model model){
        FacultiesEntity faculties = getFaculties(facultyName, budgetSeats, totalSeats);
        facultiesRepository.save(faculties);
        return "redirect:/faculties";
    }

    private FacultiesEntity getFaculties(String facultyName, int budgetSeats, int totalSeats) {
        FacultiesEntity faculties = new FacultiesEntity();
        faculties.setFacultyName(facultyName);
        faculties.setBudgetSeats(budgetSeats);
        faculties.setTotalSeats(totalSeats);
        return faculties;
    }
}

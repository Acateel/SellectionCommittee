package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import com.SelectionCommittee.SelectionCommittee.models.FacultiesEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.FacultiesRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Log4j2
public class FacultiesModerationController {

    @Autowired
    protected FacultiesRepository facultiesRepository;

    @GetMapping("/delete")
    public String deleteFaculty(@RequestParam int facultyId, Model model) {
        log.info("Delete faculty id={}", facultyId);
        facultiesRepository.deleteById((long) facultyId);
        return "redirect:/faculties";
    }

    @GetMapping("/add_faculty")
    public String getAddFacultyForm(Model model) {
        log.info("get add faculty form");
        return "admin/add_faculty";
    }

    @PostMapping("/add_faculty")
    public String addFaculty(@RequestParam(name = "faculty_name") String facultyName,
                             @RequestParam(name = "budget_seats") int budgetSeats,
                             @RequestParam(name = "total_seats") int totalSeats,
                             Model model) {
        log.info("Add faculty in DB");
        FacultiesEntity faculties = getFaculties(facultyName, budgetSeats, totalSeats);
        facultiesRepository.save(faculties);
        return "redirect:/faculties";
    }

    protected FacultiesEntity faculties;

    @GetMapping("/change_faculty")
    public String getChangeFacultyForm(@RequestParam int facultyId, Model model) {
        log.info("get change faculty form, Id={}", facultyId);
        Optional<FacultiesEntity> faculty = facultiesRepository.findById(Long.valueOf(facultyId));
        faculties = faculty.get();
        model.addAttribute("faculty", faculties);
        return "admin/change_faculty";
    }

    @PostMapping("/change_faculty")
    public String changeFaculty(@RequestParam(name = "faculty_name") String facultyName,
                                @RequestParam(name = "budget_seats") int budgetSeats,
                                @RequestParam(name = "total_seats") int totalSeats,
                                Model model) {
        log.info("Change faculty");
        faculties.setFacultyName(facultyName);
        faculties.setBudgetSeats(budgetSeats);
        faculties.setTotalSeats(totalSeats);
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

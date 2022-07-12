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

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Faculties moderation controller - get CRUD operation for faculties
 */
@Controller
@Log4j2
public class FacultiesModerationController {

    @Autowired
    protected FacultiesRepository facultiesRepository;

    private static final String REDIRECT_FACULTIES = "redirect:/faculties";

    /**
     * Delete faculty from DB
     *
     * @param facultyId faculty id
     * @return redirect faculties table page
     */
    @GetMapping("/delete")
    public String deleteFaculty(@RequestParam int facultyId) {
        log.info("Delete faculty id={}", facultyId);
        facultiesRepository.deleteById((long) facultyId);
        return REDIRECT_FACULTIES;
    }

    /**
     * show add faculty form page
     *
     * @return model name add faculty page
     */
    @GetMapping("/add_faculty")
    public String getAddFacultyForm() {
        log.info("get add faculty form");
        return "admin/add_faculty";
    }

    /**
     * Add faculty into DB
     *
     * @param facultyName faculty name
     * @param budgetSeats budget seats count
     * @param totalSeats  total seats count
     * @return redirect faculties table page
     */
    @PostMapping("/add_faculty")
    public String addFaculty(@RequestParam(name = "faculty_name") String facultyName,
                             @RequestParam(name = "budget_seats") int budgetSeats,
                             @RequestParam(name = "total_seats") int totalSeats) {
        log.info("Add faculty in DB");
        FacultiesEntity faculties = getFaculties(facultyName, budgetSeats, totalSeats);
        facultiesRepository.save(faculties);
        return REDIRECT_FACULTIES;
    }

    protected FacultiesEntity saveFaculties;

    /**
     * Show change faculty form page
     *
     * @param facultyId faculty id
     * @param model     for add attributes
     * @return model name change faculty page
     * @throws NoSuchElementException if faculty not found
     */
    @GetMapping("/change_faculty")
    public String getChangeFacultyForm(@RequestParam int facultyId, Model model) {
        log.info("get change faculty form, Id={}", facultyId);
        Optional<FacultiesEntity> faculty = facultiesRepository.findById((long) facultyId);
        if (faculty.isEmpty()) {
            log.error("Faculty not found, Id={}", facultyId);
            throw new NoSuchElementException("Faculty not found, Id=" + facultyId);
        }
        saveFaculties = faculty.get();
        model.addAttribute("faculty", saveFaculties);
        return "admin/change_faculty";
    }

    /**
     * Change faculty
     *
     * @param facultyName faculty name
     * @param budgetSeats budget seats count
     * @param totalSeats  total seats count
     * @return redirect faculties table page
     */
    @PostMapping("/change_faculty")
    public String changeFaculty(@RequestParam(name = "faculty_name") String facultyName,
                                @RequestParam(name = "budget_seats") int budgetSeats,
                                @RequestParam(name = "total_seats") int totalSeats) {
        log.info("Change faculty");
        saveFaculties.setFacultyName(facultyName);
        saveFaculties.setBudgetSeats(budgetSeats);
        saveFaculties.setTotalSeats(totalSeats);
        facultiesRepository.save(saveFaculties);
        return REDIRECT_FACULTIES;
    }

    /**
     * Create faculty entity
     *
     * @param facultyName faculty name
     * @param budgetSeats budget seats count
     * @param totalSeats  total seats count
     * @return faculty entity
     */
    private FacultiesEntity getFaculties(String facultyName, int budgetSeats, int totalSeats) {
        FacultiesEntity faculties = new FacultiesEntity();
        faculties.setFacultyName(facultyName);
        faculties.setBudgetSeats(budgetSeats);
        faculties.setTotalSeats(totalSeats);
        return faculties;
    }
}

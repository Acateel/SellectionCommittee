package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import com.SelectionCommittee.SelectionCommittee.models.FacultiesEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.FacultiesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FacultiesModerationControllerTest {
    @Autowired
    FacultiesModerationController facultiesModerationController;
    FacultiesRepository facultiesRepository;
    Model model;

    @BeforeEach
    void setUp() {
        model = mock(Model.class);
        facultiesRepository = mock(FacultiesRepository.class);
        facultiesModerationController.facultiesRepository = facultiesRepository;
        when(facultiesRepository.findById(1L)).thenReturn(Optional.of(new FacultiesEntity()));
    }

    @Test
    void deleteFaculty() {
        String template = facultiesModerationController.deleteFaculty(1, model);
        verify(facultiesRepository).deleteById(1L);
        assertEquals("redirect:/faculties", template);
    }

    @Test
    void getAddFacultyForm() {
        String template = facultiesModerationController.getAddFacultyForm(model);
        assertEquals("admin/add_faculty", template);
    }

    @Test
    void addFaculty() {
        String template = facultiesModerationController.addFaculty(
                "Faculty name",
                5,
                10,
                model
        );
        verify(facultiesRepository).save(any());
        assertEquals("redirect:/faculties", template);
    }

    @Test
    void getChangeFacultyForm() {
        String template = facultiesModerationController.getChangeFacultyForm(1, model);
        assertEquals("admin/change_faculty", template);
    }

    @Test
    void changeFaculty() {
        facultiesModerationController.faculties = new FacultiesEntity();
        String template = facultiesModerationController.changeFaculty(
                "Faculty name",
                5,
                10,
                model
        );
        verify(facultiesRepository).save(any());
        assertEquals("redirect:/faculties", template);
    }
}
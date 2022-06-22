package com.SelectionCommittee.SelectionCommittee.controllers;

import com.SelectionCommittee.SelectionCommittee.models.FacultiesEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.FacultiesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FacultiesControllerTest {

    @Autowired
    FacultiesController facultiesController;
    Model model;
    FacultiesRepository facultiesRepository;

    List<FacultiesEntity> faculties;

    @BeforeEach
    void setUp() {
        model = mock(Model.class);
        facultiesRepository = mock(FacultiesRepository.class);
        faculties = new ArrayList<>();
        when(facultiesRepository.findAllByOrderByFacultyNameAsc()).thenReturn(faculties);
        when(facultiesRepository.findAllByOrderByFacultyNameDesc()).thenReturn(faculties);
        when(facultiesRepository.findAllByOrderByBudgetSeatsDesc()).thenReturn(faculties);
        when(facultiesRepository.findAllByOrderByTotalSeatsDesc()).thenReturn(faculties);
        when(facultiesRepository.findAll()).thenReturn(faculties);
        facultiesController.facultiesRepository = facultiesRepository;
    }

    @Test
    void showFacultiesByOrder_ByName() {
        String templates = facultiesController.showFacultiesByOrder("byName", model);

        assertEquals("faculties", templates);
        verify(model).addAttribute(FacultiesController.FACULTIES_ATTRIBUTE_NAME, (Iterable<FacultiesEntity>)faculties);
        verify(facultiesRepository).findAllByOrderByFacultyNameAsc();
    }

    @Test
    void showFacultiesByOrder_byNameRevers() {
        String templates = facultiesController.showFacultiesByOrder("byNameRevers", model);

        assertEquals("faculties", templates);
        verify(model).addAttribute(FacultiesController.FACULTIES_ATTRIBUTE_NAME, (Iterable<FacultiesEntity>)faculties);
        verify(facultiesRepository).findAllByOrderByFacultyNameDesc();
    }

    @Test
    void showFacultiesByOrder_byBudget() {
        String templates = facultiesController.showFacultiesByOrder("byBudget", model);

        assertEquals("faculties", templates);
        verify(model).addAttribute(FacultiesController.FACULTIES_ATTRIBUTE_NAME, (Iterable<FacultiesEntity>)faculties);
        verify(facultiesRepository).findAllByOrderByBudgetSeatsDesc();
    }

    @Test
    void showFacultiesByOrder_byTotal() {
        String templates = facultiesController.showFacultiesByOrder("byTotal", model);

        assertEquals("faculties", templates);
        verify(model).addAttribute(FacultiesController.FACULTIES_ATTRIBUTE_NAME, (Iterable<FacultiesEntity>)faculties);
        verify(facultiesRepository).findAllByOrderByTotalSeatsDesc();
    }

    @Test
    void showFacultiesByOrder_byId() {
        String templates = facultiesController.showFacultiesByOrder("byId", model);

        assertEquals("faculties", templates);
        verify(model).addAttribute(FacultiesController.FACULTIES_ATTRIBUTE_NAME, (Iterable<FacultiesEntity>)faculties);
        verify(facultiesRepository).findAll();
    }
}
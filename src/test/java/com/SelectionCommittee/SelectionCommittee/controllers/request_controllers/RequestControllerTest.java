package com.SelectionCommittee.SelectionCommittee.controllers.request_controllers;

import com.SelectionCommittee.SelectionCommittee.models.FacultiesEntity;
import com.SelectionCommittee.SelectionCommittee.models.RequestEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.FacultiesRepository;
import com.SelectionCommittee.SelectionCommittee.repositories.RequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class RequestControllerTest {

    @Autowired
    RequestController requestController;
    FacultiesRepository facultiesRepository;
    RequestRepository requestRepository;
    Model model;
    FacultiesEntity faculties;

    Optional<FacultiesEntity> optionalFaculties;
    List<RequestEntity> requestEntityList;

    @BeforeEach
    void setUp() {
        model = mock(Model.class);
        facultiesRepository = mock(FacultiesRepository.class);
        requestRepository = mock(RequestRepository.class);
        requestController.requestRepository = requestRepository;
        requestController.facultiesRepository = facultiesRepository;
        faculties = new FacultiesEntity();
        optionalFaculties = Optional.of(faculties);
        when(facultiesRepository.findById(1L)).thenReturn(optionalFaculties);
        requestEntityList = new ArrayList<>();
        when(requestRepository.findAllByFacultiesIdOrderByRatingScoreDesc(1, PageRequest.of(0, 7))).thenReturn(requestEntityList);
    }
    @Test
    void showRequest() {
        String templates = requestController.showRequest(1, 0, model);
        assertEquals("request", templates);
    }
}
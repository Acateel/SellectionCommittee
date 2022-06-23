package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import com.SelectionCommittee.SelectionCommittee.models.RequestEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.RequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RequestsModerationControllerTest {
    @Autowired
    RequestsModerationController requestsModerationController;
    RequestRepository requestRepository;
    Model model;
    RequestEntity request;

    @BeforeEach
    void setUp() {
        model = mock(Model.class);
        requestRepository = mock(RequestRepository.class);
        requestsModerationController.requestRepository = requestRepository;
        request = new RequestEntity();
        when(requestRepository.findById(1L)).thenReturn(Optional.of(request));
    }

    @Test
    void addToRealize_not_processed() {
        request.setStatus("not processed");
        String template = requestsModerationController.addToRealize(1,1, model);
        assertEquals("budget", request.getStatus());
        verify(requestRepository).save(request);
        assertEquals("redirect:/request?facultyId=1", template);
    }

    @Test
    void addToRealize_budget() {
        request.setStatus("budget");
        String template = requestsModerationController.addToRealize(1,1, model);
        assertEquals("not processed", request.getStatus());
        verify(requestRepository).save(request);
        assertEquals("redirect:/request?facultyId=1", template);
    }
}
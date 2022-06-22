package com.SelectionCommittee.SelectionCommittee.controllers.request_controllers;

import com.SelectionCommittee.SelectionCommittee.models.ApplicantEntity;
import com.SelectionCommittee.SelectionCommittee.models.UserEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.ApplicantRepository;
import com.SelectionCommittee.SelectionCommittee.repositories.RequestRepository;
import com.SelectionCommittee.SelectionCommittee.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SendRequestControllerTest {
    @Autowired
    SendRequestController sendRequestController;
    Model model;
    Principal principal;
    UserRepository userRepository;
    ApplicantRepository applicantRepository;
    RequestRepository requestRepository;
    UserEntity user;
    ApplicantEntity applicant;

    @BeforeEach
    void setUp() {
        model = mock(Model.class);
        principal = mock(Principal.class);
        when(principal.getName()).thenReturn("user");
        userRepository = mock(UserRepository.class);
        sendRequestController.userRepository = userRepository;
        user = new UserEntity();
        user.setApplicantId(1L);
        when(userRepository.findByLogin("user")).thenReturn(Optional.of(user));

        applicantRepository = mock(ApplicantRepository.class);
        sendRequestController.applicantRepository = applicantRepository;
        applicant = new ApplicantEntity();
        applicant.setBlock((byte) 0);
        when(applicantRepository.findById(1L)).thenReturn(Optional.of(applicant));

        requestRepository = mock(RequestRepository.class);
        sendRequestController.requestRepository = requestRepository;
        when(requestRepository.findByFacultiesIdAndApplicantId(1,1L)).thenReturn(null);
    }

    @Test
    void getSendRequestForm() {
        String templates = sendRequestController.getSendRequestForm(1, model);
        assertEquals("send_request", templates);
    }

    @Test
    void sendRequestIntoDb() {
        sendRequestController.getSendRequestForm(1, model);
        String templates = sendRequestController.sendRequestIntoDb(
                180,
                180,
                180,
                11.2,
                model,
                principal
        );
        assertEquals("main", templates);
    }
}
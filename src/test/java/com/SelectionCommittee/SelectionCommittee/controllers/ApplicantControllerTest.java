package com.SelectionCommittee.SelectionCommittee.controllers;

import com.SelectionCommittee.SelectionCommittee.models.ApplicantEntity;
import com.SelectionCommittee.SelectionCommittee.models.UserEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.ApplicantRepository;
import com.SelectionCommittee.SelectionCommittee.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ApplicantControllerTest {

    @Autowired
    ApplicantController applicantController;
    Model model;
    Principal principal;
    ApplicantRepository applicantRepository;
    UserRepository userRepository;
    String login;
    Long applicantId;

    ApplicantEntity applicant;

    MultipartFile file;

    @BeforeEach
    void setUp() throws IOException {
        login = "login";
        applicantId = 1L;
        UserEntity user = new UserEntity();
        user.setApplicantId(applicantId);
        applicant = new ApplicantEntity();

        model = mock(Model.class);
        principal = mock(Principal.class);
        when(principal.getName()).thenReturn(login);

        userRepository = mock(UserRepository.class);
        applicantController.userRepository = userRepository;
        when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));

        applicantRepository = mock(ApplicantRepository.class);
        applicantController.applicantRepository = applicantRepository;
        when(applicantRepository.findById(applicantId)).thenReturn(Optional.of(applicant));

        file = mock(MultipartFile.class);
        when(file.getBytes()).thenReturn(null);
    }

    @Test
    void getUserInfo() {
        String template = applicantController.getUserInfo(model, principal);

        verify(model).addAttribute("applicant", applicant);
        assertEquals("applicant", template);
    }

    @Test
    void uploadAttestation() {
        String template = applicantController.uploadAttestation(file, model, principal);

        verify(applicantRepository).save(applicant);
        assertEquals("applicant", template);
    }
}
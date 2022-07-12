package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import com.SelectionCommittee.SelectionCommittee.models.ApplicantEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.ApplicantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ApplicantsModerationControllerTest {

    @Autowired
    ApplicantsModerationController applicantsModerationController;
    Model model;
    ApplicantRepository applicantRepository;

    @BeforeEach
    void setUp() {
        model = mock(Model.class);
        applicantRepository = mock(ApplicantRepository.class);
        applicantsModerationController.applicantRepository = applicantRepository;
        when(applicantRepository.findAll()).thenReturn(new ArrayList<>());
        when(applicantRepository.findById(1L)).thenReturn(Optional.of(new ApplicantEntity()));
    }

    @Test
    void getApplicants() {
        String template = applicantsModerationController.getApplicants(0,model);
        assertEquals("admin/applicants", template);
    }

    @Test
    void blockApplicant() {
        String template = applicantsModerationController.blockApplicant(1);
        verify(applicantRepository).save(any());
        assertEquals("redirect:/applicants", template);
    }

    @Test
    void deblockApplicant() {
        String template = applicantsModerationController.deblockApplicant(1);
        verify(applicantRepository).save(any());
        assertEquals("redirect:/applicants", template);
    }
}
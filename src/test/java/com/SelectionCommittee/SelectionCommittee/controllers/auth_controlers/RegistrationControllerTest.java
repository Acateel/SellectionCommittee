package com.SelectionCommittee.SelectionCommittee.controllers.auth_controlers;

import com.SelectionCommittee.SelectionCommittee.models.ApplicantEntity;
import com.SelectionCommittee.SelectionCommittee.models.UserEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.ApplicantRepository;
import com.SelectionCommittee.SelectionCommittee.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RegistrationControllerTest {

    @Autowired
    RegistrationController registrationController;
    UserRepository userRepository;
    ApplicantRepository applicantRepository;
    Model model;

    @BeforeEach
    void setUp() {
        model = mock(Model.class);
        userRepository = mock(UserRepository.class);
        registrationController.userRepository = userRepository;
        when(userRepository.findByLogin("someemail@gmail.com")).thenReturn(Optional.empty());
        when(userRepository.findByLogin("emailExist@gmail.com")).thenReturn(Optional.of(new UserEntity()));
        applicantRepository = mock(ApplicantRepository.class);
        registrationController.applicantRepository = applicantRepository;
        when(applicantRepository.findByLastNameAndNameAndSurname("Lastname","Firstname","Surname"))
                .thenReturn(new ApplicantEntity());
    }

    @Test
    void getRegistrationForm() {
        String template = registrationController.getRegistrationForm(model);
        assertEquals("auth/register", template);
    }

    @Test
    void registration() {
        String template = registrationController.registration(
                "someemail@gmail.com",
                "sometext1",
                "sometext1",
                "Lastname",
                "Firstname",
                "Surname",
                "City",
                "Region",
                "Education",
                model
        );
        verify(applicantRepository).save(any());
        verify(userRepository).save(any());
        assertEquals("auth/login", template);
    }

    @Test
    void registration_false_password() {
        String template = registrationController.registration(
                "someemail@gmail.com",
                "some",
                "sometext1",
                "Lastname",
                "Firstname",
                "Surname",
                "City",
                "Region",
                "Education",
                model
        );
        verify(applicantRepository, never()).save(any());
        verify(userRepository, never()).save(any());
        assertEquals("auth/register", template);
    }

    @Test
    void registration_user_exist() {

        String template = registrationController.registration(
                "emailExist@gmail.com",
                "sometext1",
                "sometext1",
                "Lastname",
                "Firstname",
                "Surname",
                "City",
                "Region",
                "Education",
                model
        );
        verify(applicantRepository, never()).save(any());
        verify(userRepository, never()).save(any());
        assertEquals("auth/register", template);
    }
}
package com.SelectionCommittee.SelectionCommittee.controllers.auth_controlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class LoginControllerTest {

    @Autowired
    LoginController loginController;
    Model model;

    @BeforeEach
    void setUp() {
        model = mock(Model.class);
    }

    @Test
    void login() {
        String template = loginController.login(model);
        assertEquals("auth/login", template);
    }
}
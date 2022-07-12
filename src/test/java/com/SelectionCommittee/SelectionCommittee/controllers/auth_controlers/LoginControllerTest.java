package com.SelectionCommittee.SelectionCommittee.controllers.auth_controlers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LoginControllerTest {

    @Autowired
    LoginController loginController;


    @Test
    void login() {
        String template = loginController.login();
        assertEquals("auth/login", template);
    }
}
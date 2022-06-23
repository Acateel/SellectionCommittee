package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class AdminMenuControllerTest {

    @Autowired
    AdminMenuController adminMenuController;
    Model model;

    @BeforeEach
    void setUp() {
        model = mock(Model.class);
    }

    @Test
    void getAdminMenu() {
        String template = adminMenuController.getAdminMenu(model);
        assertEquals("admin/menu", template);
    }
}
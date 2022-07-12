package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AdminMenuControllerTest {

    @Autowired
    AdminMenuController adminMenuController;

    @Test
    void getAdminMenu() {
        String template = adminMenuController.getAdminMenu();
        assertEquals("admin/menu", template);
    }
}
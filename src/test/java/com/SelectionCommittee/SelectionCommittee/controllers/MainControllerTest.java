package com.SelectionCommittee.SelectionCommittee.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Main Controller Test - testing MainController
 */
@SpringBootTest
class MainControllerTest {
    @Autowired
    MainController controller;

    /**
     * Testing return template's name of main page
     */
    @Test
    void main() {
        String model = controller.main();
        assertEquals("main", model);
    }
}
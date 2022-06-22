package com.SelectionCommittee.SelectionCommittee.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class MainControllerTest {
    @Autowired
    MainController controller;

    @Test
    void main() {
        Model modelMock = mock(Model.class);
        String model = controller.main(modelMock);
        assertEquals("main", model);
    }
}
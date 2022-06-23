package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import com.SelectionCommittee.SelectionCommittee.finalizers.Finalizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
class FinalizeControllerTest {

    @Autowired
    FinalizeController finalizeController;
    Model model;
    Finalizer finalizer;
    @BeforeEach
    void setUp() {
        model = mock(Model.class);
        finalizer = mock(Finalizer.class);
        finalizeController.finalizer = finalizer;
    }

    @Test
    void finalizeRequests() {
        String template = finalizeController.finalizeRequests(model);
        verify(finalizer).finalizeRequests();
        assertEquals("main", template);
    }
}
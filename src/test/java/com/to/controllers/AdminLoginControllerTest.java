package com.to.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;

class AdminLoginControllerTest {
    @InjectMocks
    private AdminLoginController adminLoginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSomeLoginEndpoint() {
        // TODO: Add test logic for AdminLoginController
    }
}

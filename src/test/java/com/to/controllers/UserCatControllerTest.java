package com.to.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import com.to.entities.Category;
import com.to.services.CategoryService;

class UserCatControllerTest {
    @InjectMocks
    private UserCatController userCatController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSomeCatEndpoint() {
        // TODO: Add test logic for UserCatController
    }

    @Test
    void testGetAllCat() {
        Category cat1 = new Category();
        cat1.setCid(1);
        cat1.setCname("Category1");
        Category cat2 = new Category();
        cat2.setCid(2);
        cat2.setCname("Category2");
        List<Category> mockList = Arrays.asList(cat1, cat2);
        when(categoryService.getAllCat()).thenReturn(mockList);

        List<Category> result = userCatController.getAllCat();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Category1", result.get(0).getCname());
        assertEquals("Category2", result.get(1).getCname());
        verify(categoryService, times(1)).getAllCat();
    }
}
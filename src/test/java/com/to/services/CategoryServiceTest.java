package com.to.services;

import com.to.entities.Category;
import com.to.entities.Product;
import com.to.exceptions.EtBadRequestException;
import com.to.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductService productService;
    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCat() {
        Category cat = new Category();
        when(categoryRepository.save(cat)).thenReturn(cat);
        assertEquals(cat, categoryService.saveCat(cat));
    }

    @Test
    void testGetAllCat() {
        List<Category> cats = Arrays.asList(new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(cats);
        assertEquals(cats, categoryService.getAllCat());
    }

    @Test
    void testGetCat() {
        Category cat = new Category();
        when(categoryRepository.findById(1)).thenReturn(Optional.of(cat));
        assertEquals(cat, categoryService.getCat(1));
        when(categoryRepository.findById(2)).thenReturn(Optional.empty());
        assertNull(categoryService.getCat(2));
    }

    @Test
    void testUpdateCat() {
        Category oldCat = new Category();
        oldCat.setCid(1);
        oldCat.setCname("Old");
        oldCat.setActive(true);
        Category newCat = new Category();
        newCat.setCid(1);
        newCat.setCname("New");
        newCat.setActive(false);
        when(categoryRepository.findById(1)).thenReturn(Optional.of(oldCat));
        when(categoryRepository.save(any(Category.class))).thenAnswer(i -> i.getArgument(0));
        Category updated = categoryService.updateCat(newCat);
        assertEquals("New", updated.getCname());
        assertFalse(updated.isActive());
    }

    @Test
    void testDeleteCategory_WhenCategoryNotInUse() {
        Category cat = new Category();
        cat.setCid(1);
        when(productService.getAllCatProducts(1)).thenReturn(Collections.emptyList());
        doNothing().when(categoryRepository).deleteById(1);
        assertDoesNotThrow(() -> categoryService.deleteCategory(cat));
        verify(categoryRepository).deleteById(1);
    }

    @Test
    void testDeleteCategory_WhenCategoryInUse() {
        Category cat = new Category();
        cat.setCid(1);
        when(productService.getAllCatProducts(1)).thenReturn(Arrays.asList(new Product()));
        assertThrows(EtBadRequestException.class, () -> categoryService.deleteCategory(cat));
        verify(categoryRepository, never()).deleteById(anyInt());
    }
}

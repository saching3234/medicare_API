package com.to.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.to.entities.Category;
import com.to.entities.Product;
import com.to.services.ProductService;

class UserProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private UserProductController userProductController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducst() {
        Product p1 = new Product();
        Product p2 = new Product();
        when(productService.getAllProducts()).thenReturn(Arrays.asList(p1, p2));
        List<Product> result = userProductController.getAllProducst();
        assertThat(result).hasSize(2);
        verify(productService).getAllProducts();
    }

    @Test
    void testGetCatProducts() {
        Category category = new Category();
        category.setCid(1);
        Product activeProduct = new Product();
        activeProduct.setActive(true);
        Product inactiveProduct = new Product();
        inactiveProduct.setActive(false);
        when(productService.getAllCatProducts(1)).thenReturn(Arrays.asList(activeProduct, inactiveProduct));
        List<Product> result = userProductController.getCatProducts(category);
        assertThat(result).containsExactly(activeProduct);
        verify(productService).getAllCatProducts(1);
    }

    @Test
    void testGetProductByBrandName() {
        Product input = new Product();
        input.setBrand("BrandA");
        Product p1 = new Product();
        p1.setBrand("BrandA");
        Product p2 = new Product();
        p2.setBrand("BrandB");
        when(productService.getAllProducts()).thenReturn(Arrays.asList(p1, p2));
        List<Product> result = userProductController.getProductByBrandName(input);
        assertThat(result).containsExactly(p1);
        verify(productService).getAllProducts();
    }
}
package com.to.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import com.to.entities.Product;
import com.to.services.ProductService;


@ExtendWith(MockitoExtension.class)
class AdminProductControllerTest {
	
    @Mock
	ProductService productService;
    @InjectMocks
    AdminProductController adminProductController;
	

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testGetAllProducts() {
		List<Product> mockProducts = new ArrayList<>();
		when(productService.getAllProducts()).thenReturn(mockProducts);
		assertNotNull(adminProductController.getAllProducts(), "Product list should not be null");
	}
	
	@Test
	void testGetAllProductsThrowsException() {
        when(productService.getAllProducts()).thenThrow(new RuntimeException("DB error"));
        // Since the controller does not handle the exception, it should propagate
        assertThatThrownBy(() -> adminProductController.getAllProducts())
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("DB error");
        verify(productService).getAllProducts();
    }

    @Test
	void testSaveProductThrowsException() throws Exception {
        MultipartFile file = mock(MultipartFile.class);
        String prod = "{\"pid\":1}";
        when(productService.saveProduct(any(Product.class))).thenThrow(new RuntimeException("Save error"));
        assertThatThrownBy(() -> adminProductController.saveProduct(file, prod))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Save error");
    }

    @Test
    void testGetProductThrowsException() {
        Product product = new Product();
        product.setPid(1);
        when(productService.getProduct(1)).thenThrow(new RuntimeException("Get error"));
        assertThatThrownBy(() -> adminProductController.getProduct(product))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Get error");
    }

    @Test
    void testDeleteProductThrowsException() {
        Product product = new Product();
        when(productService.deleteProduct(product)).thenThrow(new RuntimeException("Delete error"));
        assertThatThrownBy(() -> adminProductController.deleteProduct(product))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Delete error");
    }

    @Test
    void testUpdateProductThrowsException() {
        Product product = new Product();
        when(productService.updtaeProduct(product)).thenThrow(new RuntimeException("Update error"));
        assertThatThrownBy(() -> adminProductController.updateProduct(product))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Update error");
    }

    @Test
    void testUpdateProductImgThrowsException() throws Exception {
        MultipartFile file = mock(MultipartFile.class);
        String prod = "{\"pid\":1}";
        when(productService.updtaeProduct(any(Product.class))).thenThrow(new RuntimeException("UpdateImg error"));
        assertThatThrownBy(() -> adminProductController.updateProductImg(file, prod))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("UpdateImg error");
    }
}
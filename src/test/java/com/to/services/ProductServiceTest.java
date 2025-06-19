package com.to.services;

import com.to.entities.Product;
import com.to.repositories.ProductRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProduct() {
        Product product = Instancio.create(Product.class);
        when(productRepository.save(product)).thenReturn(product);
        Product saved = productService.saveProduct(product);
        assertThat(saved).isEqualTo(product);
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = Instancio.ofList(Product.class).size(3).create();
        when(productRepository.findAll()).thenReturn(products);
        List<Product> result = productService.getAllProducts();
        assertThat(result).hasSize(3);
    }

    @Test
    void testDeleteProduct() {
        Product product = Instancio.create(Product.class);
        doNothing().when(productRepository).deleteById(product.getPid());
        String result = productService.deleteProduct(product);
        assertThat(result).contains(String.valueOf(product.getPid()));
        verify(productRepository).deleteById(product.getPid());
    }

    @Test
    void testUpdtaeProduct() {
        Product product = Instancio.create(Product.class);
        Product existing = Instancio.create(Product.class);
        existing.setPid(product.getPid());
        when(productRepository.findById(product.getPid())).thenReturn(Optional.of(existing));
        when(productRepository.save(any(Product.class))).thenAnswer(i -> i.getArgument(0));
        Product updated = productService.updtaeProduct(product);
        assertThat(updated.getPname()).isEqualTo(product.getPname());
        verify(productRepository).save(existing);
    }

    @Test
    void testGetAllCatProducts() {
        int cid = 5;
        List<Product> products = Instancio.ofList(Product.class).size(2).create();
        when(productRepository.findBycid(cid)).thenReturn(products);
        List<Product> result = productService.getAllCatProducts(cid);
        assertThat(result).hasSize(2);
    }

    @Test
    void testGetProduct() {
        Product product = Instancio.create(Product.class);
        when(productRepository.findById(product.getPid())).thenReturn(Optional.of(product));
        Product found = productService.getProduct(product.getPid());
        assertThat(found).isEqualTo(product);
    }
}

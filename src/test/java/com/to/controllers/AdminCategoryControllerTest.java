package com.to.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.apache.catalina.startup.CertificateCreateRule;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.to.entities.Category;
import com.to.services.CategoryService;


@ExtendWith(MockitoExtension.class)
class AdminCategoryControllerTest {
	
	@InjectMocks
	AdminCategoryController adminCategoryResource;
	@Mock
	CategoryService categoryService;
	
	@Test
	void testGetAllCategory() {
        Category category=Instancio.create(Category.class);
		when(categoryService.getAllCat()).thenReturn(Arrays.asList(category));
		List<Category> allCategory = adminCategoryResource.getAllCategory();
		assertNotNull(allCategory);
	}
	
	@Test
	void testGetCat() {
		Category category=Instancio.create(Category.class);
		when(categoryService.getCat(category.getCid())).thenReturn(category);
		Category cat = adminCategoryResource.getCat(category);
		assertEquals(category.getCid(), cat.getCid());
	}
	
	@Test
	void testSaveCategory() {
		Category category=Instancio.create(Category.class);
		when(categoryService.saveCat(category)).thenReturn(category);
		Category cat = adminCategoryResource.saveCategory(category);
		assertEquals(category.getCid(), cat.getCid());
	}
	
	@Test
	void testUpdatCategory() {
		Category category=Instancio.create(Category.class);
		when(categoryService.updateCat(category)).thenReturn(category); 
		Category cat = adminCategoryResource.updatCategory(category);
		assertEquals(category.getCid(), cat.getCid());
	}
	
	@Test
	void testDeleteCat() { 
	 Category category=Instancio.create(Category.class);	
	 adminCategoryResource.deleteCat(category);
	 verify(categoryService, times(1)).deleteCategory(category);
	}

}

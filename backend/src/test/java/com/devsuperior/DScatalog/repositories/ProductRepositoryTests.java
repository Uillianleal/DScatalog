package com.devsuperior.DScatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.DScatalog.entities.Product;
import com.devsuperior.DScatalog.tests.Factory;

@DataJpaTest
public class ProductRepositoryTests {
	
	@Autowired
	private ProductRepository repository;
	
	private long exintingId;
	private long noExistingId;
	private long countTotalPrrducts = 25L;
	
	@BeforeEach
	void setUp() throws Exception {
		exintingId = 1L;
		noExistingId = 1000L;
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		 
		Product product = Factory.creatProduct();
		product.setId(null);
		
		product = repository.save(product);
		
		Assertions.assertNotNull(product.getId());
		Assertions.assertEquals(countTotalPrrducts + 1, product.getId());
		
	}
	
	@Test
	public void deleteShouldObjectWhenIdExists() {
		
		repository.deleteById(1L);
		
		Optional<Product> result = repository.findById(exintingId);
		
		Assertions.assertFalse(result.isPresent());
		
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(noExistingId);
		});
	}
	
	@Test
	public void findByIdShouldReturntOptionalWhenIdExists() {
		Optional<Product> obj = repository.findById(exintingId);
		Assertions.assertTrue(obj.isPresent());
		
	}
	
	@Test
	public void findByIdShouldReturnEmptyOptionalWhenIdNotExist() {		
			Optional<Product> obj = repository.findById(noExistingId);
			Assertions.assertTrue(obj.isEmpty());
	}
	
}

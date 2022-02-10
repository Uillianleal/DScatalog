package com.devsuperior.DScatalog.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devsuperior.DScatalog.dto.ProductDTO;
import com.devsuperior.DScatalog.entities.Category;
import com.devsuperior.DScatalog.entities.Product;
import com.devsuperior.DScatalog.repositories.CategoryRepository;
import com.devsuperior.DScatalog.repositories.ProductRepository;
import com.devsuperior.DScatalog.services.exception.DatabaseException;
import com.devsuperior.DScatalog.services.exception.ResourceNotFoundException;
import com.devsuperior.DScatalog.tests.Factory;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
	
	@InjectMocks
	private ProductService service;
	
	@Mock
	private ProductRepository repository;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	private long existingId;
	private long noExistingId;
	private long dependentId;
	private Category category;
	private PageImpl<Product> page;
	ProductDTO productDto;
	private Product product;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		noExistingId = 1000L;
		dependentId = 4L;
		productDto = Factory.creatProductDTO();
		product = Factory.creatProduct();
		category = Factory.createCategory();
		page = new PageImpl<>(List.of(product));
		
		Mockito.when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(product);
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));
		Mockito.when(repository.findById(noExistingId)).thenReturn(Optional.empty());
		
		Mockito.when(repository.getOne(existingId)).thenReturn(product);
		Mockito.when(repository.getOne(noExistingId)).thenThrow(EntityNotFoundException.class);
		
		Mockito.when(categoryRepository.getOne(existingId)).thenReturn(category);
		Mockito.when(categoryRepository.getOne(noExistingId)).thenThrow(EntityNotFoundException.class);
	
		Mockito.doNothing().when(repository).deleteById(existingId);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(noExistingId);
		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);	

	}
	
	
	@Test
	public void updateShouldReturnWhenIdExists() {
	
		ProductDTO result = service.update(existingId, productDto);
		Assertions.assertNotNull(result);
	
	}
	
	@Test
	public void updateShouldTrhowResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(noExistingId, productDto);
		});

	}
	
	
	
	@Test
	public void findByIdShouldTrhowResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(noExistingId);
		});

	}
	
	
	@Test
	public void findByIdShouldReturnProductDTOWhenIdExists() {
		
		ProductDTO result = service.findById(existingId);
		Assertions.assertNotNull(result);
	
	}
	
	
	
	@Test
	public void findAllPagedShouldReturnPage() {
		
		Pageable pageable = PageRequest.of(0, 10);
		
		Page<ProductDTO> result = service.findAllPaged(pageable);
		
		Assertions.assertNotNull(result);
		Mockito.verify(repository, Mockito.times(1)).findAll(pageable);
	}
	
	
	@Test
	public void deleteShouldTrhowDatabaseExceptionWhenDependentId() {
		
		Assertions.assertThrows(DatabaseException.class, () -> {
			service.delete(dependentId);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(dependentId);
	}
	
	
	@Test
	public void deleteShouldTrhowResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(noExistingId);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(noExistingId);
	}
	

	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
	}
	
	
	
	
}

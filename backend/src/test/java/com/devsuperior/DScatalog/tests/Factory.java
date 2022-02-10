package com.devsuperior.DScatalog.tests;

import java.time.Instant;

import com.devsuperior.DScatalog.dto.ProductDTO;
import com.devsuperior.DScatalog.entities.Category;
import com.devsuperior.DScatalog.entities.Product;

public class Factory {
	
	public static Product creatProduct() {
		Product product = new Product(1L, "Phone", "Good Phone", 800.0, "https://img.com/img.png", Instant.parse("2020-10-20T03:00:00Z"));
		product.getCategories().add(createCategory());
		return product;
	}
	
	public static ProductDTO creatProductDTO() {
		Product product = creatProduct();
		return new ProductDTO(product, product.getCategories()) ;
	}
	
	public static Category createCategory() {
		return new Category(2L, "Electronics");
	}
}

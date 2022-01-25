package com.devsuperior.DScatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.DScatalog.entities.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	//Camada de acesso a dados
}

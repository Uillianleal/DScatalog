package com.devsuperior.DScatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.DScatalog.entities.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	//Camada de acesso a dados
}

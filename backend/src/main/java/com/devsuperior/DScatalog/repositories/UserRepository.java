package com.devsuperior.DScatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.DScatalog.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	//Camada de acesso a dados
}
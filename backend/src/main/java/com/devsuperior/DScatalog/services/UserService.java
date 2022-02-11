package com.devsuperior.DScatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.DScatalog.dto.RoleDTO;
import com.devsuperior.DScatalog.dto.UserDTO;
import com.devsuperior.DScatalog.dto.UserInsertDTO;
import com.devsuperior.DScatalog.dto.UserUpdateDTO;
import com.devsuperior.DScatalog.entities.Role;
import com.devsuperior.DScatalog.entities.User;
import com.devsuperior.DScatalog.repositories.RoleRepository;
import com.devsuperior.DScatalog.repositories.UserRepository;
import com.devsuperior.DScatalog.services.exception.DatabaseException;
import com.devsuperior.DScatalog.services.exception.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAllPaged(Pageable pageble){
		Page<User> page = repository.findAll(pageble);
		return page.map(x -> new UserDTO(x));
		 
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new UserDTO(entity);
	}

	
	@Transactional(readOnly = true)
	public UserDTO insert(UserInsertDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity.setPassword(encoder.encode(dto.getPassword()));
		entity = repository.save(entity);
		return new UserDTO(entity);
		
	}
	

	@Transactional(readOnly = true)
	public UserDTO update(Long id, UserUpdateDTO dto) {
		try	{
			User entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UserDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
		
	}
	
	
	private void copyDtoToEntity(UserDTO dto, User entity) {
		
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
				
		entity.getRoles().clear();
		for (RoleDTO roleDto : dto.getRoles()) {
			Role role = roleRepository.getOne(roleDto.getId());
			entity.getRoles().add(role);
		}
		
	}
}

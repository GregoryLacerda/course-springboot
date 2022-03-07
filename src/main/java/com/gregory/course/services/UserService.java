package com.gregory.course.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gregory.course.entities.User;
import com.gregory.course.repositories.UserRepository;
import com.gregory.course.services.exceptions.DatabaseException;
import com.gregory.course.services.exceptions.ResourceNotFoundException;

//A classe precisa ser registrada como um componente do framework(Spring)
@Service // anotação pra registrar @Component, @Repository e @Service 
public class UserService {
	
	
	@Autowired// injeção de dependencia auto do springboot
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);//essa operação retorna um tipo Optional
		// codigo para tentar realizar a operação de buscar pelo id e caso de erro com lambda ele lança a exceção
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	//inserindo no banco um novo objeto user
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);	
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}
	
	public User update(Long id, User obj) {
		try {
			User entity = findById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());		
	}
	
}

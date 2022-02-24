package com.gregory.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gregory.course.entities.User;
import com.gregory.course.repositories.UserRepository;

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
		return obj.get();
	}
}

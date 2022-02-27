package com.gregory.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gregory.course.entities.Category;
import com.gregory.course.repositories.CategoryRepository;

//A classe precisa ser registrada como um componente do framework(Spring)
@Service // anotação pra registrar @Component, @Repository e @Service 
public class CategoryService {
	
	
	@Autowired// injeção de dependencia auto do springboot
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);//essa operação retorna um tipo Optional
		return obj.get();
	}
}

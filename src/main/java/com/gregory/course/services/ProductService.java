package com.gregory.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gregory.course.entities.Product;
import com.gregory.course.repositories.ProductRepository;

//A classe precisa ser registrada como um componente do framework(Spring)
@Service // anotação pra registrar @Component, @Repository e @Service 
public class ProductService {
	
	
	@Autowired// injeção de dependencia auto do springboot
	private ProductRepository repository;
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);//essa operação retorna um tipo Optional
		return obj.get();
	}
}

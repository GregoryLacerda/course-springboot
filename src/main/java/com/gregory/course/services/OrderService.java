package com.gregory.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gregory.course.entities.Order;
import com.gregory.course.repositories.OrderRepository;

//A classe precisa ser registrada como um componente do framework(Spring)
@Service // anotação pra registrar @Component, @Repository e @Service 
public class OrderService {
	
	
	@Autowired// injeção de dependencia auto do springboot
	private OrderRepository repository;
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);//essa operação retorna um tipo Optional
		return obj.get();
	}
}

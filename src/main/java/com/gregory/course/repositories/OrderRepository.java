package com.gregory.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gregory.course.entities.Order;

//Não precisa colocar o @Repository pois ja está herdando do JPARepository 
public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
// apena com esta implementação ja está pronto, 
//pois o JPARepositry ja tem todas a implementações necessarias 
//Fazendo isso a inteface ja está com as implementações
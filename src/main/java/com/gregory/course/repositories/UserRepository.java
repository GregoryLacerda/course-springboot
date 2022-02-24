package com.gregory.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gregory.course.entities.User;

//Não precisa colocar o @Repository pois ja está herdando do JPARepository 
public interface UserRepository extends JpaRepository<User, Long>{
	
}
// apena com esta implementação ja está pronto, 
//pois o JPARepositry ja tem todas a implementações necessarias 
//Fazendo isso a inteface ja está com as implementações
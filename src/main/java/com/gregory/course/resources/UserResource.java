package com.gregory.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gregory.course.entities.User;

//para informar que é um recurso web e implementado por um controller
@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	//para informar que o metodo responde a requisição get do http usa o @ abaixo
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "maria", "maria@gamil", "16565464", "123");
		return ResponseEntity.ok().body(u);
	}
}

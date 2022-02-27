package com.gregory.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gregory.course.entities.Product;
import com.gregory.course.services.ProductService;

//para informar que é um recurso web e implementado por um controller
@RestController
//para informar onde a responsta vai estar
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	//para informar que o metodo responde a requisição get do http usa o @ abaixo
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> list = service.findAll(); 
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")//o value para que a requisição aceite uma informação, o di
	public ResponseEntity<Product> findById(@PathVariable Long id){ // @PathVariable é para aceitar o parametro que vem da url
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}

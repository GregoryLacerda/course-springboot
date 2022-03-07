package com.gregory.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gregory.course.entities.User;
import com.gregory.course.services.UserService;

//para informar que é um recurso web e implementado por um controller
@RestController
//para informar onde a responsta vai estar
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	//para informar que o metodo responde a requisição get do http usa o @ abaixo
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll(); 
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")//o value para que a requisição aceite uma informação, o di
	public ResponseEntity<User> findById(@PathVariable Long id){ // @PathVariable é para aceitar o parametro que vem da url
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//forma correta de inserir um recurso no banco de dados
	@PostMapping//post para fazer cadastrar um dado 
	public ResponseEntity<User> insert(@RequestBody User obj){//@RequestBody para mudar do json que vai vimpara o obj user no java
		obj = service.insert(obj);
		//linha de codigo para criar uma url location que é necessaria para o created
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj); //created usado para que a resposta http deja 201, padrão para quando se insere uma informação
	}
	
	@DeleteMapping(value = "/{id}")//anotation padrão para deletar objeto 
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();//noContent pois é um metodo que a responsta não tem corpo, e o codigo será 204
	}
}

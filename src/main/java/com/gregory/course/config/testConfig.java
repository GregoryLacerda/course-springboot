package com.gregory.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gregory.course.entities.Order;
import com.gregory.course.entities.User;
import com.gregory.course.repositories.OrderRepository;
import com.gregory.course.repositories.UserRepository;

 
@Configuration//Anotação que informa que a classe será de configuração
@Profile("test")//e especifica para o perfil de test
public class testConfig implements CommandLineRunner{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired//faz com que o spring boot resolve a dependencia e associa uma instancia do UserRepository
	private UserRepository userRepository;

	//implementar a CommandLineRunner para executar quando a aplicação for iniciado usando o metodo abaixo
	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1); 
		
		//saveall precisa de uma lista, arrays.aslist cria a lista com os dois objetos
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		
	}
	
	
}

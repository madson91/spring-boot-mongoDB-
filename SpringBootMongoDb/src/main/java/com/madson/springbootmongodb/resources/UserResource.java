package com.madson.springbootmongodb.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.madson.springbootmongodb.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		
		User maria = new User("1", "maria", "maria@mail.com");
		User jose = new User("2", "jose", "jose@mail.com");
		List<User> lista = new ArrayList<>();
		lista.addAll(Arrays.asList(maria,jose));
		return ResponseEntity.ok().body(lista);
		
	}
	
	

}

package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("api/user")
public class UserController {
	
	@Autowired
	public UserRepository userrepo;

	@GetMapping
	public List<User> findAllUsers(){
		List<User> a= (List<User>) userrepo.findAll();
		return a;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)	
	public ResponseEntity<Object> findById(@PathVariable(value="id") long id) {
		Optional<User> o= userrepo.findById(id);
		if(o.isPresent()) {
			return ResponseEntity.ok().body(o.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public User saveUser(@RequestBody User user) {
		return userrepo.save(user);
	}
}

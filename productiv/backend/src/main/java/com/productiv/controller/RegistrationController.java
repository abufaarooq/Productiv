package com.productiv.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.productiv.model.User;
import com.productiv.service.RegistrationService;

@RestController
@RequestMapping("/api/v1")
public class RegistrationController
{
	@Autowired
	RegistrationService register;
	

	@PostMapping("/add")
	public ResponseEntity<String> addUser (@RequestBody User user) {
	User payload = register.addUser(user);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(payload.getId()).toUri();
	return new ResponseEntity<String> ("Thank you for registering, " + user.getFirstName() + ".", HttpStatus.CREATED);
//	return ResponseEntity.created(uri).build();
}
	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteItem (@PathVariable Long id) {
		register.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
package com.productiv.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.productiv.model.Item;
import com.productiv.service.ItemService;

@RestController
@CrossOrigin(origins = "localhost:4200")
public class ItemController {
	
	@Autowired
	private ItemService service;
	
	@GetMapping("/users/{userName}/items")
	public  List<Item> getAllItems(@PathVariable String userName)
	{
		return service.findAll();
	}
	
	// @GetMapping("/users/{user}/items")
	// public  List<Item> getItemByUserName(@PathVariable User user)
	// {
	// 	return service.findItemByUserName(user);
	// }

	@GetMapping("/users/{userName}/items/{id}")
	public  Item getItem(@PathVariable String userName, @PathVariable long id)
	{
		return service.findById(id);
	}

	@DeleteMapping("/users/{userName}/items/{id}")
	public ResponseEntity<Void> deleteItem (@PathVariable String userName, @PathVariable long id) {
		
		service.deleteId(id);
		
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/users/{userName}/items")
	public ResponseEntity<Void> postItems (@PathVariable String userName, @RequestBody Item item) {
	
	Item postItem = service.create(item);	
	
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postItem.getId()).toUri();

	return ResponseEntity.created(uri).build();
	
	}
	@PutMapping("/users/{userName}/items/{id}")
	public ResponseEntity<Item> updateItems (@PathVariable String userName, @PathVariable long id, @RequestBody Item item) {
	
	service.update(item);
		
	return new ResponseEntity <Item> (item, HttpStatus.OK);
	
	}
}
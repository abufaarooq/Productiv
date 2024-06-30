package com.productiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productiv.model.Item;
import com.productiv.model.User;
import com.productiv.repository.ItemsRepository;

@Service
public class ItemService 
{
	@Autowired
    private ItemsRepository repository;
	
//	@Autowired
//	private User user;
	
	public Item findItemByUserName (User user)
	{
		Item item = new Item();
		item.setUser(user);
		return item;
	}

	
	public Item postItem(Item item)
	{
		
		return repository.save(item);
	}
	

	public Item create(Item item)
	{
		return repository.save(item);
	}
    public List<Item> findAll()
	{
		return repository.findAll();
	}

    public Item findById(Long id) {
		
        return repository.findById(id).get();
		
	}

    public String deleteId(Long id)
	{
    	repository.deleteById(id);
		return "Item number, " + id  + " has been deleted!";
	}
	
	public Item update (Item item)
	{
		return repository.save(item);
	}


}

package com.productiv.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productiv.model.Item;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Long> 
{
	// @Query("select * from item a, end_users b where b.userName = username")
	// public Item findItemByUserName (String username);
// public Item postItem (Item item);
// public Item getItem (Item item);
// public Item deleteItem (Item item);
// public Item updateItem (Item item);


}
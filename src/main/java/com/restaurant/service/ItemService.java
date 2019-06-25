package com.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.models.Item;
import com.restaurant.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepo;
	
	public void salvarItem( Item item ) {
		
		itemRepo.save(item);
		
	}
	
}

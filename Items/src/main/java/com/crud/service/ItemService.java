package com.crud.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.crud.entites.Item;
import com.crud.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	// CREATE
	public Item createItem(Item item) {
		item.setCreatedAt(LocalDateTime.now());
		item.setUpdatedAt(LocalDateTime.now());
		return itemRepository.save(item);
	}

	// READ (All)
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	// READ (With Filter)
	public List<Item> getItemsByStatus(String status) {
		return itemRepository.findByStatus(status);
	}

	// READ (With Specification - for dynamic filtering)
	public List<Item> getItemsBySpecification(Specification<Item> spec) {
		return itemRepository.findAll(spec);
	}

	// READ (By ID)
	public Optional<Item> getItemById(Long id) {
		return itemRepository.findById(id);
	}

	// UPDATE
	public Optional<Item> updateItem(Long id, Item updatedItem) {
		return itemRepository.findById(id).map(existingItem -> {
			existingItem.setName(updatedItem.getName());
			existingItem.setStatus(updatedItem.getStatus());
			existingItem.setCategory(updatedItem.getCategory());
			existingItem.setQuantity(updatedItem.getQuantity());
			existingItem.setPrice(updatedItem.getPrice());
			existingItem.setUpdatedAt(LocalDateTime.now());
			return itemRepository.save(existingItem);
		});
	}

	@Transactional
	// DELETE (By ID)
	public void deleteItem(Long id) {
		itemRepository.deleteById(id);
	}

	@Transactional
	// DELETE (By Condition: e.g., status = "inactive")
	public void deleteItemsByStatus(String status) {
		List<Item> inactiveItems = itemRepository.findByStatus(status);
		itemRepository.deleteAll(inactiveItems);
	}
}

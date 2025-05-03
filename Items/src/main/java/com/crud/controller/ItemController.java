package com.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.dto.CreateItemDTO;
import com.crud.entites.Item;
import com.crud.service.ItemService;
import com.crud.specification.ItemSpecification;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {

	@Autowired
	private ItemService itemService;

	// CREATE

	@PostMapping
	public ResponseEntity<Item> createItem(@RequestBody CreateItemDTO dto) {
		Item item = new Item();
		item.setName(dto.getName());
		item.setStatus(dto.getStatus());
		item.setCategory(dto.getCategory());
		item.setQuantity(dto.getQuantity());
		item.setPrice(dto.getPrice());
		return ResponseEntity.ok(itemService.createItem(item));
	}

	// READ ALL
	@GetMapping
	public ResponseEntity<List<Item>> getAllItems(@RequestParam(required = false) String name,
			@RequestParam(required = false) String status, @RequestParam(required = false) String category) {
		Specification<Item> spec = Specification.where(null);
		if (name != null) {
			spec = spec.and(ItemSpecification.hasName(name));
		}
		if (status != null) {
			spec = spec.and(ItemSpecification.hasStatus(status));
		}
		if (category != null) {
			spec = spec.and(ItemSpecification.hasCategory(category));
		}
		return ResponseEntity.ok(itemService.getItemsBySpecification(spec));
	}

	// READ BY ID
	@GetMapping("/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable Long id) {
		Optional<Item> item = itemService.getItemById(id);
		return item.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<Optional<Item>> updateItem(@PathVariable Long id, @RequestBody CreateItemDTO dto) {

		Item item = new Item();
		item.setId(id); // Set the existing ID
		item.setName(dto.getName());
		item.setStatus(dto.getStatus());
		item.setCategory(dto.getCategory());
		item.setQuantity(dto.getQuantity());
		item.setPrice(dto.getPrice());

		// Call the service to update the item
		Optional<Item> updatedItem = itemService.updateItem(id, item);
		return ResponseEntity.ok(updatedItem);
	}

	// DELETE BY ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
		itemService.deleteItem(id);
		return ResponseEntity.noContent().build();
	}

	// DELETE BY STATUS (e.g., inactive)
	@DeleteMapping("/deleteByStatus")
	public ResponseEntity<Void> deleteItemsByStatus(@RequestParam String status) {
		itemService.deleteItemsByStatus(status);
		return ResponseEntity.noContent().build();
	}
}

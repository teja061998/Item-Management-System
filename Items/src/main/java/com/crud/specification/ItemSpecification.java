package com.crud.specification;

import org.springframework.data.jpa.domain.Specification;

import com.crud.entites.Item;

public class ItemSpecification {

	public static Specification<Item> hasName(String name) {
		return (root, query, builder) -> builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
	}

	public static Specification<Item> hasStatus(String status) {
		return (root, query, builder) -> builder.equal(root.get("status"), status);
	}

	public static Specification<Item> hasCategory(String category) {
		return (root, query, builder) -> builder.like(builder.lower(root.get("category")),
				"%" + category.toLowerCase() + "%");
	}
}

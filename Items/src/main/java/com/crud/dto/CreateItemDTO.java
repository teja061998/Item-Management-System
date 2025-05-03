package com.crud.dto;

public class CreateItemDTO {
	private String name;
	private String status;
	private String category;
	private int quantity;
	private double price;

	public CreateItemDTO(String name, String status, String category, int quantity, double price) {
		super();
		this.name = name;
		this.status = status;
		this.category = category;
		this.quantity = quantity;
		this.price = price;
	}

	public CreateItemDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
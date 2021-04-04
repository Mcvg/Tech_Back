package com.api.restservice;

public class Product {

	private String sku;
	private String name;
	private int price;

	public Product(String sku, String name, int price) {
		super();
		this.sku = sku;
		this.name = name;
		this.price = price;
	}

	public String getSku() {
		return sku;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}

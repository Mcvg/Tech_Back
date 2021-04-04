package com.api.restservice;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TechBackApplication {
	public ArrayList<Product> listProducts = new ArrayList();

	Client client = new Client("Cédula de Ciudadanía", "12345", "Accenture", "business", "Medellín",
			"Carrera 11# 14-08");

	public void main(String[] args) {
		addProducts();
		SpringApplication.run(TechBackApplication.class, args);
	}

	protected void addProducts() {
		listProducts.add(new Product("A1", "Portátil", 1200000));
		listProducts.add(new Product("A2", "Mouse", 30000));
		listProducts.add(new Product("A3", "Teclado", 70000));
		listProducts.add(new Product("A4", "Base para portátil", 40000));
	}

}

package com.api.restservice;

public class Client {

	private final String typeId;
	private final String id;
	private final String name;
	private final String lastName;
	private final String city;
	private final String address;

	public Client(String typeId, String id, String name, String lastName, String city, String address) {
		super();
		this.typeId = typeId;
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.city = city;
		this.address = address;
	}

	public String getTypeId() {
		return typeId;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

}

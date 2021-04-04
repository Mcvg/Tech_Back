package com.api.restservice;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Order {

	private AtomicLong id;
	private String idClient;
	private List<Map<String, Object>> product;
	private double value;
	private double iva;
	private double netValue;
	private double priceDomicile;
	private long time;

	public Order(AtomicLong id, String idClient, List<Map<String, Object>> product, double value, double iva,
			double netValue, double priceDomicile, long time) {
		super();
		this.id = id;
		this.idClient = idClient;
		this.product = product;
		this.value = value;
		this.iva = iva;
		this.netValue = netValue;
		this.priceDomicile = priceDomicile;
		this.time = time;
	}

	public AtomicLong getId() {
		return id;
	}

	public String getIdClient() {
		return idClient;
	}

	public List<Map<String, Object>> getProduct() {
		return product;
	}

	public double getValue() {
		return value;
	}

	public double getIva() {
		return iva;
	}

	public double getNetValue() {
		return netValue;
	}

	public double getPriceDomicile() {
		return priceDomicile;
	}

	public long getTime() {
		return time;
	}

	public void setId(AtomicLong id) {
		this.id = id;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	public void setProduct(List<Map<String, Object>> product) {
		this.product = product;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public void setNetValue(double netValue) {
		this.netValue = netValue;
	}

	public void setPriceDomicile(double priceDomicile) {
		this.priceDomicile = priceDomicile;
	}

	public void setTime(long time) {
		this.time = time;
	}

	
}

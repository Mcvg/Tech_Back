package com.api.restservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import services.OrderServices;
import services.OrderServicesImpl;

@RestController
@RequestMapping("/order")
public class OrderController extends TechBackApplication {

	OrderServices service = new OrderServicesImpl();
	static List<Order> lista = new ArrayList<Order>();
	private final AtomicLong counter = new AtomicLong();
	private static final double priceWithoutDomicile = 100000;

	@GetMapping("/getList") // anotación garantiza que las solicitudes HTTP GET /getList se asignen al
							// getList()método.
	public List<Order> getList(@RequestParam(value = "id") String id) {
		List<Order> listOrder = new ArrayList<Order>();
		for (Order order : lista) {
			if (order.getIdClient().equalsIgnoreCase(id)) {
				listOrder.add(order);
			}
		}
		return listOrder;
	}

	@PostMapping(path = "/", consumes = "application/json")
	public ResponseEntity<String> saveOrder(@RequestBody Map<String, Object> product) {
		List<Map<String, Object>> mapProduct = new LinkedList<Map<String, Object>>();
		mapProduct.add(product);
		double value = priceWithoutDomicile * 2;
		double iva = value * 0.19;
		double priceDomicile = calculatePriceDomicile(value);
		double netValue = value + iva + priceDomicile;
		long time = Calendar.getInstance().getTimeInMillis();

		lista.add(new Order(counter, client.getId(), mapProduct, value, iva, priceDomicile, netValue, time));
		return ResponseEntity.status(HttpStatus.OK).body("Se creó correctamente el pedido!!");

	}

	private double calculatePriceDomicile(double value) {
		double priceDomicile = 0;
		if (value < priceWithoutDomicile) {
			priceDomicile = 7000;
		}
		return priceDomicile;

	}

	@PostMapping(path = "/add", consumes = "application/json")
	public ResponseEntity<String> addProduct(@RequestBody Map<String, Object> product) {
		boolean edit = false;
		for (Order order : lista) {
			if (order.getIdClient().equalsIgnoreCase((String) product.get("idClient"))) {
				long time = order.getTime();
				edit = isEditOrder(time);
			}
		}
		if (!edit) {
			return ResponseEntity.status(HttpStatus.OK)
					.body("No es posible editar su pedido, ya que han trancurrido más de 5 horas desde su creación");

		}

		for (Order order : lista) {
			if (order.getIdClient().equalsIgnoreCase((String) product.get("idClient"))) {
				List<Map<String, Object>> listProducts = order.getProduct();
				listProducts.add(product);
				int price = getPriceProduct((String) product.get("sku"));
				double valueOrder = order.getValue() + price;
				double iva = valueOrder * 0.19;
				double priceDomicile = calculatePriceDomicile(valueOrder);
				double netValue = valueOrder + iva + priceDomicile;
				listProducts.add(product);
				order.setValue(valueOrder);
				order.setIva(iva);
				order.setPriceDomicile(priceDomicile);
				order.setNetValue(netValue);

				return ResponseEntity.status(HttpStatus.OK).body("Se añadió correctamente el producto al pedido!!");

			}
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error al agregar el producto");

	}

	private boolean isEditOrder(long time) {
		Date fechaFin = new Date();
		long timeNow = fechaFin.getTime();
		long substractionTime = timeNow - time;
		substractionTime = substractionTime / (1000 * 60);
		if (substractionTime < 5) {
			return true;
		}
		return false;
	}
	
	private boolean isDeleteOrder(long time) {
		Date fechaFin = new Date();
		long timeNow = fechaFin.getTime();
		long substractionTime = timeNow - time;
		substractionTime = substractionTime / (1000 * 60);
		if (substractionTime < 12) {
			return true;
		}
		return false;
	}

	private int getPriceProduct(String sku) {
		for (Product product : listProducts) {
			if (product.getSku().equalsIgnoreCase(sku)) {
				return product.getPrice();
			}
		}
		return 0;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteStudent(@PathVariable AtomicLong idOrder) {
		boolean delete;
		for (int i = 0; i <= lista.size(); i++) {
			long time = lista.get(i).getTime();
			delete = isDeleteOrder(time);
			if (lista.get(i).getId() == (idOrder) && delete) {
				lista.remove(i);
				return ResponseEntity.noContent().build();
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body("No tiene permiso para eliminar el pedido porque el tiempo ha expirado");
	}

}

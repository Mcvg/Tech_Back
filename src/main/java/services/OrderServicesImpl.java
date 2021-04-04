package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.restservice.Order;
import com.api.restservice.Product;

public class OrderServicesImpl implements OrderServices {

	static List<Order> lista=new ArrayList<Order>();
	private final AtomicLong counter = new AtomicLong();
	private static final double priceWithoutDomicile = 100000;
	  public Order saveOrder(Order order) {
		  
		  return order;
	  }
	  
	  public Boolean deleteProduct(Order person, String sku) {
		  return true;
	  }

	@Override
	public ResponseEntity<String>  saveOrder(String idClient, Product product, int units) {
		double value = product.getPrice() * units;
		double iva = value * 0.19;
		double priceDomicile = calculatePriceDomicile(value);
		double netValue = value + iva + priceDomicile;
		Calendar calendar = Calendar.getInstance();
		//lista.add(new Order(counter,idClient,product,value,iva,priceDomicile,netValue));
		return new ResponseEntity(HttpStatus.OK);
	}
	
	public double calculatePriceDomicile(double value) {
		double priceDomicile = 0;
		if(value < priceWithoutDomicile) {
			priceDomicile = 7000;
		}
		return priceDomicile;

	}
	  
}

package services;

import org.springframework.http.ResponseEntity;

import com.api.restservice.Order;
import com.api.restservice.Product;

public interface OrderServices {

	  public ResponseEntity<String> saveOrder(String idClient, Product product, int units);
	  
	  public Boolean deleteProduct(Order person, String sku);
	  
}

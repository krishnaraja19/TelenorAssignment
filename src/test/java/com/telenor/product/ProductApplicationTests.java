package com.telenor.product;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.telenor.product.ProductApplication;
import com.telenor.product.entity.Product;
import com.telenor.product.entity.Products;
import com.telenor.product.model.ErrorDetails;

@SpringBootTest(classes = ProductApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT )
class ProductApplicationTests {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	void getDataWithOutParameter() {
		String url="http://localhost:"+port+"/api/product";
		
		Map<String, List<Product>> data = this.testRestTemplate
				.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, List<Product>>> (){})
				.getBody();
		List<Product> productList = data.get("data");
		assertTrue(productList.size() == 100);
		
	}
	
	@Test
	void getDataWithType() {
		Map<String, String> inputs = new HashMap<>();
		inputs.put("type", "subscription");
		
		String url="http://localhost:"+port+"/api/product?type="+inputs.get("type");
		
		Map<String, List<Product>> data = this.testRestTemplate
				.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, List<Product>>> (){})
				.getBody();
		List<Product> productList = data.get("data");
		assertTrue(productList.size() == 58);
	}
	@Test
	void getDataWithTypeAndProperty() {
		Map<String, String> inputs = new HashMap<>();
		inputs.put("type", "phone");
		inputs.put("properties", "color:rosa");
		
		String url="http://localhost:"+port+"/api/product?type="+inputs.get("type")+"&properties="+inputs.get("properties");
		Map<String, List<Product>> data = this.testRestTemplate
				.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, List<Product>>> (){})
				.getBody();
		List<Product> productList = data.get("data");
		assertTrue(productList.size() == 5);
		
		
	}
	@Test
	void getDataWithTypeAndPropertyAsGBLimit() {
		Map<String, String> inputs = new HashMap<>();
		inputs.put("type", "subscription");
		inputs.put("properties", "gb_limit:50");
		
		String url="http://localhost:"+port+"/api/product?type="+inputs.get("type")+"&properties="+inputs.get("properties");
		Map<String, List<Product>> data = this.testRestTemplate
				.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, List<Product>>> (){})
				.getBody();
		List<Product> productList = data.get("data");
		assertTrue(productList.size() == 34);
		
		
	}
	
	@Test
	void getNoData() {
		Map<String, String> inputs = new HashMap<>();
		inputs.put("type", "phone");
		inputs.put("properties", "gb_limit:50");
		
		String url="http://localhost:"+port+"/api/product?type="+inputs.get("type")+"&properties="+inputs.get("properties");
		ErrorDetails data = this.testRestTemplate
				.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<ErrorDetails> (){})
				.getBody();
		
		assertTrue(data.getMessage().equalsIgnoreCase("Data is not available"));
		
		
	}

	@Test
	void getDataWithTypeAndPropertyAndPrice() {
		Map<String, String> inputs = new HashMap<>();
		inputs.put("type", "subscription");
		inputs.put("max_price", "37");
		inputs.put("store_address", "Stockholm");
		
		String url="http://localhost:"+port+"/api/product?type="+inputs.get("type")+"&max_price="+inputs.get("max_price")+"&store_address="+inputs.get("store_address");
		System.out.println(url);
		Map<String, List<Product>> data = this.testRestTemplate
				.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, List<Product>>> (){})
				.getBody();
		List<Product> productList = data.get("data");
		assertTrue(productList.size()== 1);
		
		
	}

}

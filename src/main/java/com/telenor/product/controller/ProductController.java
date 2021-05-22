package com.telenor.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telenor.product.configuration.ProductConfigurator;
import com.telenor.product.entity.Product;
import com.telenor.product.exception.MultipleValuesException;
import com.telenor.product.exception.ResourceNotFoundException;
import com.telenor.product.service.InputValidationService;
import com.telenor.product.service.ProductReaderService;
import com.telenor.product.service.ProductRepositoryImpl;

@RestController
@RequestMapping("/api/")
public class ProductController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductRepositoryImpl productServiceImpl;
	@Autowired
	private InputValidationService inputValid;
		
	/**
	 * Getting data from H2 database
	 * @param Type
	 * @param Properties
	 * @param Min_Price
	 * @param Max_Price
	 * @param City
	 * @return
	 * @throws MultipleValuesException 
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "product", method = RequestMethod.GET)
	public ResponseEntity<Map<String, List<Product>>> getProducts(
			@RequestParam(value = "type") Optional<String> Type,
			@RequestParam(value = "properties") Optional<String> Properties,
			@RequestParam(value = "min_price") Optional<String> Min_Price,
			@RequestParam(value = "max_price") Optional<String> Max_Price,
			@RequestParam(value = "store_address") Optional<String> storeAddress) {
		
		Map<String, List<Product>> result = new HashMap<String, List<Product>>();
		List<Product> products = null;
		
		// Getting Input validation result
		boolean isValid = inputValid.multipleProductValueException(
				Type.isPresent() ? Type.get() : null,
				Properties.isPresent() ? Properties.get() : null,
				Min_Price.isPresent() ? Min_Price.get() : null,
				Max_Price.isPresent() ? Max_Price.get() : null,
				storeAddress.isPresent() ? storeAddress.get() : null);
		
		//If validation fails throw new Exception
		if(!isValid) {
			LOGGER.info("Multiple Input value validation starting now");
			throw new MultipleValuesException("Could not able to process the multiple values in input parameter");
		}
		
		//Calling repository to get the required data
		LOGGER.info("Getting the data from Product repository");
		products = productServiceImpl.findByMultipleParameter(Type, Properties, 
				Min_Price.isPresent() ? Optional.of(Double.parseDouble(Min_Price.get())) : Optional.empty(),
				Max_Price.isPresent() ? Optional.of(Double.parseDouble(Max_Price.get())) : Optional.empty(), 
				storeAddress);
		
		//If data is not available throw resource not found exception
		if (products.isEmpty()) {
			LOGGER.info("No data available for the request");
			throw new ResourceNotFoundException("Data is not available");
			
		}
		
		//If data is availableit will send the data in response
		result.put("data", products);
		return new ResponseEntity<Map<String, List<Product>>>(result, HttpStatus.OK);
	}
	
	
}

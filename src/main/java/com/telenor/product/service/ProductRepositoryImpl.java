package com.telenor.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.telenor.product.entity.Product;
import com.telenor.product.repository.ProductRepository;

public class ProductRepositoryImpl {
	private static final Logger LOGGER=LoggerFactory.getLogger(ProductRepositoryImpl.class);
	
	@Autowired
	ProductRepository productRepo;
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	    private int batchSize;
	
	public void bulkSave(List<Product> products) {
		LOGGER.info("Saving the data into H2 inmemory database");
		List<Product> batchProduct = productRepo.saveAll(products);
			
		
	}
	public List<Product> findByMultipleParameter(Optional<String> type,Optional<String> properties,Optional<Double> minPrice,Optional<Double> maxPrice,Optional<String> storeAddress){
		
		LOGGER.info("Getting the records as per the reauest parameter");
		
		String percentage="%";
		String locType = type.map(Object::toString).orElse("");
		String locProperties = properties.map(Object::toString).orElse("");	
		Double locMinPrice = minPrice.map(p->p.doubleValue()).orElse(0.0);	
		Double locMaxPrice = maxPrice.map(p->p.doubleValue()).orElse(1000.0);
		String locAddress = storeAddress.map(Object::toString).orElse("");
		locAddress = percentage.concat(locAddress.concat(percentage));
		
		List<Product> products = productRepo.findByTypeAndProperties(locType, locProperties, locMinPrice,locMaxPrice,locAddress);
		return products;
		
	}
}

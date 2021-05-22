package com.telenor.product.entity;

import java.util.ArrayList;
import java.util.List;

public class Products {
	private List<Product> productsList;

	public List<Product> getProductsList() {
		if(productsList == null) {
			productsList = new ArrayList<>();
        }
		return productsList;
	}

	public void setProductsList(List<Product> productsList) {
		this.productsList = productsList;
	}

	
	
}

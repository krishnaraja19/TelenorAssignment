package com.telenor.product.service;

import org.springframework.stereotype.Service;

@Service
public class InputValidationService {
	private boolean isValid;
	public boolean multipleProductValueException(String productType,
			String properties, String minPrice, String maxPrice, String storeAddress) {
		boolean isTypeValid = false,isPropertyValid=false,isMinPrice=false,isMaxPrice=false,
				isStoreAddressValid=false;
		isValid = false;

		if(productType == null)
			isTypeValid=true;
		else if(lenghtofInput(productType) == 1)
			isTypeValid=true;
		
		if(properties == null)
			isPropertyValid=true;
		else if(lenghtofInput(properties) == 1)
			isPropertyValid=true;
		
		if(minPrice == null)
			isMinPrice=true;
		else if(lenghtofInput(minPrice) == 1)
			isMinPrice=true;
		
		if(maxPrice == null)
			isMaxPrice=true;
		else if(lenghtofInput(maxPrice) == 1)
			isMaxPrice=true;
		
		if(storeAddress == null)
			isStoreAddressValid=true;
		else if(lenghtofInput(storeAddress) == 2 || lenghtofInput(storeAddress) == 1)
			isStoreAddressValid=true;
		
		
		if(isTypeValid && isPropertyValid && isMinPrice && isMaxPrice && isStoreAddressValid)
			isValid = true;
		
		return isValid;
		
	}
	
	public int lenghtofInput(String input) {
		String[] data = input.split(",");
		return data.length;
	}
}

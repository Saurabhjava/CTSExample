package com.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.exception.ProductNotFoundException;
import com.model.Product;

//use appropriate annotation to configure ShoppingService as a Service
@Service
public class EmiService {

	public long calculateEmi(Product product) {
		double rate = 0.0;
		if (getProductInterestRate().containsKey(product.getProductName())) {
			rate = getProductInterestRate().get(product.getProductName());
			double r = rate / (12 * 100);
			double p = product.getLoanAmount();
			double t = product.getLoanTenure();
			double emi=(p * r * Math.pow(1 + r, t)) / (Math.pow(1 + r, t) - 1);
			return Math.round(emi);
		} else {
			throw new ProductNotFoundException("Product is not Available");
		}

	}

	// Dont change this code
	public Map<String, Double> getProductInterestRate() {
		Map<String, Double> stock = new HashMap<String, Double>();
		stock.put("Home Loan", 10.50);
		stock.put("Car Loan", 12.50);
		stock.put("Personal Loan", 17.50);

		return stock;
	}
}

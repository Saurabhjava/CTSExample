package com.model;

import javax.validation.constraints.Min;

//Use appropriate annotation
public class Product {
	private String productName ;
	//Use appropriate annotation
	@Min(value = 10000, message = "Minimum Loan Amount should be 10000")
	private double loanAmount;	
	//Use appropriate annotation
	@Min(value = 12, message = "Minimum Tenure should be 12 months")
	private double loanTenure;
	
	public Product() {}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(double loanTenure) {
		this.loanTenure = loanTenure;
	}
	
}

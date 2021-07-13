package com.cts.bean;

import java.math.BigDecimal;

public class CurrencyConversionBean {
	private String from;
	private String to;
	private int quantity;
	private BigDecimal conversionRate;
	private BigDecimal totalAmount;
	private int port;
	
	
	public CurrencyConversionBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CurrencyConversionBean(String from, String to, int quantity, BigDecimal conversionRate,
			BigDecimal totalAmount, int port) {
		super();
		this.from = from;
		this.to = to;
		this.quantity = quantity;
		this.conversionRate = conversionRate;
		this.totalAmount = totalAmount;
		this.port=port;
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getConversionRate() {
		return conversionRate;
	}


	public void setConversionRate(BigDecimal conversionRate) {
		this.conversionRate = conversionRate;
	}


	public BigDecimal getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}
	
	

}

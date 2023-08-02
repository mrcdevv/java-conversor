package com.aluraconversor.model;

public class Monedas {
	private String symbol;
	private String description;
	
	public Monedas(String symbol, String description) {
		this.symbol = symbol;
		this.description = description;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
}

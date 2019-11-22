package com.jeantravassos.packageservice.dto;

public class Package {

	private Integer index;
	private Double weight;
	private Double price;
	
	
	public Package(Integer index, Double weight, Double price) {
		super();
		this.index = index;
		this.weight = weight;
		this.price = price;
	}
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Package [index=" + index + ", weight=" + weight + ", price=" + price + "]";
	}
	
	
}

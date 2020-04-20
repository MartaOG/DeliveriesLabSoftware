package com.vehicles.project;

public class Wheel {
	private String brand;
	private double diameter;

	public Wheel(String brand, double diameter) {
		this.brand = brand;
		this.diameter = diameter;
	}
	
	public boolean equals(Wheel e) {
		return ((this.brand.compareTo(e.brand) == 0) && this.diameter == e.diameter);
	}
}

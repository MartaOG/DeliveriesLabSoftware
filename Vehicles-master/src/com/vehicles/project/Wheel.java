package com.vehicles.project;

import java.util.List;

public class Wheel {
	private String brand;
	private double diameter;

	public Wheel(String brand, double diameter) {
		this.brand = brand;
		this.diameter = diameter;
	}
	
	public double getDiameter() { return this.diameter; }
	public String getBrand() { return this.brand; }
	
	public boolean checkDiameter () {
		return this.diameter >= 0.4 && this.diameter <= 4.0;
	}
	
	public boolean equals (Wheel wheel) {
		return this.diameter == wheel.diameter;
	}
}

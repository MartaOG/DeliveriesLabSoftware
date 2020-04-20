package com.vehicles.project;

import java.util.List;

public class Bike extends Vehicle {

	public Bike(String plate, String brand, String color) {
		super(plate, brand, color);
	}
	
	public void addTwoWheels(List<Wheel> front,List<Wheel> back) throws Exception {
		if (front.size() != 1 && back.size() != 1) {
			throw new Exception();
		}
		this.wheels.add(front.get(0));
		this.wheels.add(back.get(0));
	}
}

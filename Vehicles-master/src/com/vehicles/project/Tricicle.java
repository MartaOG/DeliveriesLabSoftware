package com.vehicles.project;

import java.util.List;

public class Tricicle extends Vehicle{
	
	public Tricicle (String plate, String brand, String color) {
		super(plate, brand, color);
	}
	
	public void addThreeWheels(List<Wheel> front,List<Wheel> back) throws Exception {
		if (front.size() != 1 && back.size() != 2) {
			throw new Exception();
		}


		if (!(back.get(0).equals(back.get(1)))) {
			throw new Exception();
		}

		this.wheels.add(back.get(0));
		this.wheels.add(back.get(1));
		this.wheels.add(front.get(0));
	}
}

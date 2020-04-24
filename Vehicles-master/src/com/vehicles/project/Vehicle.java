package com.vehicles.project;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {

	protected String plate;
	protected String brand;
	protected String color;
	protected List<Wheel> wheels = new ArrayList<Wheel>();

	public Vehicle(String plate, String brand, String color) {
		this.plate = plate;
		this.brand = brand;
		this.color = color;
	}
	
	public static void checkPlate (String plate) throws Exception {
		if (plate.length() != 7 || !isNumber(plate.substring(0, 4)) || !correctLetters(plate.substring(4, 7))) throw new Exception();	
	}
	
	public static boolean isNumber (String numbersOfPlate) {
		try {
			Integer.parseInt(numbersOfPlate);
			return true;
		} catch (NumberFormatException nfe) { return false; }	
	}
	
	public static boolean correctLetters (String lettersOfPlate) {
		
		for (int x = 0; x < lettersOfPlate.length(); x ++ ) {
			
			char letter = lettersOfPlate.charAt(x);
			
			if (!((letter >= 'A') && (letter <= 'Z')) || letter == ' ') return false;		
		}
		
		return true;
	}
}

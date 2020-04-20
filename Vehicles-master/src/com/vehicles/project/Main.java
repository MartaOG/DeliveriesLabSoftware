package com.vehicles.project;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		Car myCar = (Car)createVehicle(scn, 'C');
		
		
		//3 Afegir rodes de darrere
		List<Wheel> backWheels = new ArrayList<Wheel>();
		List<Wheel> frontWheels = new ArrayList<Wheel>();

		System.out.println("Back wheels");
		backWheels = addWheels(scn);
		
		//4
		System.out.println("Front wheels");
		frontWheels = addWheels(scn);
		
		
		//3 i 4
		try{
			myCar.addWheels(frontWheels, backWheels);
		}
		catch(Exception e) {
			System.out.println("We are having errors with your wheels, please try it again.");
		}
	}

	public static Vehicle createVehicle(Scanner scn, char whichVehicle) {
		String p = plate(scn);
		String b = input(scn, "Write the brand: ");
		String c = input(scn, "Write the colou: ");
					
		switch(whichVehicle) {
		case 'C': return new Car(p, b, c);
		case 'B': return new Bike(p, b, c);
		default: return null;
		}
	}
	
	private static String input (Scanner scn, String a) {
		System.out.println(a);
		return scn.next();
	}
	
	private static String plate (Scanner scn) {
		String p = input(scn, "Write the plate: ");
		
		p = p.toUpperCase();
		try {
			checkPlate(p);
		}
		catch(Exception e) {
			System.out.println("Invalid plate");
			System.exit(0);
		}
		return p;
	}
	
	public static void checkPlate (String p) throws Exception {
		if (p.length() != 7 || !isNumber(p.substring(0, 4)) || !correctLetters(p.substring(4, 7))) throw new Exception();	
	}
	
	private static boolean isNumber (String p) {
		try {
			Integer.parseInt(p);
			return true;
		} catch (NumberFormatException nfe) { return false; }	
	}
	
	private static boolean correctLetters (String p) {
		for (int x = 0; x < p.length(); x ++ ) {
			char y = p.charAt(x);
			if (!((y >= 'A') && (y <= 'Z')) || y == ' ') return false;
		}
		return true;
	}

	private static List<Wheel> addWheels(Scanner scn) {
		List<Wheel>twoWheels = new ArrayList<Wheel>();
		
		Wheel w = createOneWheel(scn);
		twoWheels.add(w);
		twoWheels.add(w);
		
		return twoWheels;
	}
	
	private static Wheel createOneWheel (Scanner scn) {
		System.out.print("Write the wheel diamter: ");
		double dmt = scn.nextDouble();

		try {
			checkDiameter(dmt);
		} catch (Exception e) {
			System.out.println("Invalid diameter.");
			System.exit(0);
		}
		System.out.print("Write the wheel brand: ");
		
		String brnd =  scn.next();
		
		return new Wheel(brnd, dmt);
	}
	
	public static void checkDiameter (double n) throws Exception {
		if (n < 0.4 || n > 4.0) throw new Exception ();
	}

}

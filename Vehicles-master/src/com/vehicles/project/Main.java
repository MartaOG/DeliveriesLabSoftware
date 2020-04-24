package com.vehicles.project;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		Vehicle v;
		
		char x = menu(scn);
	
		if (x == 'C' || x == 'c') 
			v = createCar(scn);
		else if (x == 'B' || x == 'b')
			v = createBike(scn);
		else 
			v = createTricicle(scn);
		
		List<Wheel> frontWheels = askForFrontWheels(scn, v, "Front wheels");	
		List<Wheel> backWheels = askForBackWheels(scn, v, "Back wheels");
		
		checkWheels(v, frontWheels, backWheels);
		
		System.out.println("END");
	}

	private static String input (Scanner scn, String a) {
		System.out.println(a);
		return scn.next();
	}
	
	private static char menu (Scanner scn) {
		char m;	
		
		System.out.println("What type of vehicle do you want to create?");
		do {
			System.out.println("Bike(B)/Car(C)/Tricicle(T)");
			m = scn.next().charAt(0);
		} while (m != 'C' && m != 'c' && m != 'b' && m != 'B' && m != 't' && m != 'T');	
		
		return m;
	}
	
	public static Vehicle createVehicle (Scanner scn, char type){
		String p = plate(scn);
		String b = input(scn, "Write the brand: ");
		String c = input(scn, "Write the color: ");
					
		switch(type) {
		case 'C': return new Car(p, b, c);
		case 'B': return new Bike(p, b, c);
		default: return new Tricicle(p, b, c);
		}
	}
	
	public static Car createCar (Scanner scn) {
		return (Car)createVehicle(scn, 'C');
	}
	
	private static Bike createBike (Scanner scn) {
		return (Bike)createVehicle(scn, 'B');
	}
	
	private static Tricicle createTricicle (Scanner scn) {
		return (Tricicle)createVehicle(scn, 'T');
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

	private static List<Wheel> askForFrontWheels (Scanner scn, Vehicle v, String s) {
		System.out.println(s);
		if (v instanceof Car) 	
			return addTwoWheels(scn);
		
		else if (v instanceof Bike) 
			return addOneWheel(scn);
		
		else return addOneWheel(scn);
	}
	
	private static List<Wheel> askForBackWheels (Scanner scn, Vehicle v, String s) {
		System.out.println(s);
		if (v instanceof Bike) 	
			return addOneWheel(scn);
		
		else if (v instanceof Car) 
			return addTwoWheels(scn);
		
		else return addTwoWheels(scn);
	}
	
	private static List<Wheel> addTwoWheels(Scanner scn) {	
		return addXWheels(scn, 2);
	}
	
	
	private static List<Wheel> addOneWheel (Scanner scn) {
		return addXWheels (scn, 1);
	}
	
	private static List<Wheel> addXWheels (Scanner scn, int x) {
		List<Wheel> toRet = new ArrayList<Wheel>();
		while (x > 0) {
			toRet.add(createOneWheel(scn));
			x --;
		}
		return toRet;
	}
	
 	private static Wheel createOneWheel (Scanner scn) {
		System.out.print("Put the wheel diamter: ");
		double dmt = scn.nextDouble();
		try {
			checkDiameter(dmt);
		} catch (Exception e) {
			System.out.println("Invalid diameter.");
			System.exit(0);
		}
		
		String brnd = input(scn, "Write the wheel brand: ");
		
		return new Wheel(brnd, dmt);
	}
	
	private static void checkDiameter (double n) throws Exception {
		if (n < 0.4 || n > 4.0) throw new Exception ();
	}
	
	private static void checkWheels (Vehicle v, List<Wheel>frontWheels, List<Wheel>backWheels) {
		if (v instanceof Car) {
			try{
				((Car)v).addWheels(frontWheels, backWheels);
			} catch(Exception e) {
				System.out.println("We are having errors with your wheels, please try it again.");
			}			
		}
		else if (v instanceof Bike) {
			try {
				((Bike)v).addTwoWheels(frontWheels, frontWheels);
			} catch(Exception e) {
				System.out.println("We are having errors with your wheels, please try it again.");
			}
		}
		else {
			
		}
	}
	
}

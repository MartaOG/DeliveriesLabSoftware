package com.vehicles.project;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		//1 Demanar a l'usuari matricula, marca i color
		System.out.print("Plate: ");
		String p = scn.next();
		p = p.toUpperCase();
		try {
			checkPlate(p);
		}
		catch(Exception e) {
			System.out.println("Invalid plate");
			System.exit(0);
		}
		
		System.out.print("Brand: ");
		String b = scn.next();
		
		System.out.print("Colour: ");
		String c = scn.next();
		
		//2 Crear un coche
		
		Car myCar = new Car(p, b, c);
				
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

	private static List<Wheel> addWheels(Scanner s) {
		List<Wheel>twoWheels = new ArrayList<Wheel>();
		
		Wheel w = createOneWheel(s);
		twoWheels.add(w);
		twoWheels.add(w);
		
		return twoWheels;
	}
	
	private static Wheel createOneWheel (Scanner s) {
		System.out.print("Write the wheel diamter: ");
		double dmt = s.nextDouble();

		try {
			checkDiameter(dmt);
		} catch (Exception e) {
			System.out.println("Invalid diameter.");
			System.exit(0);
		}
		System.out.print("Write the wheel brand: ");
		
		String brnd =  s.next();
		
		return new Wheel(brnd, dmt);
	}
	
	//Fase 2
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
	
	public static void checkDiameter (double n) throws Exception {
		if (n < 0.4 || n > 4.0) throw new Exception ();
	}

}

package com.vehicles.project;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static final int CAR = 1;
	static final int BIKE = 2;
	static final int TRICICLE = 3;
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		Vehicle vehicle;
		
		int typeOfVehicle = askForTypeOfVehicle(scn); 
	
		switch(typeOfVehicle) {
		case CAR: vehicle = createCar(scn); break;
		case BIKE: vehicle = createBike(scn); break;
		case TRICICLE: vehicle = createTricicle(scn); break;
		default: vehicle = null; break;
		}
		
		try {
			Vehicle.checkPlate(vehicle.plate);
		}
		catch(Exception e) {
			System.out.println("Invalid plate");
		}
		
		System.out.println("Front wheels: ");
		List<Wheel> frontWheels = askForFrontWheels(scn, vehicle);	
		System.out.println("Back wheels: ");
		List<Wheel> backWheels = askForBackWheels(scn, vehicle);
		
		checkWheels(vehicle, frontWheels, backWheels);
		
		System.out.println("END");
	}

	private static String input (Scanner scn, String sentences) {
		System.out.println(sentences);
		return scn.next();
	}
	
	private static int inputInteger (Scanner scn, String sentences) {
		System.out.println(sentences);
		return scn.nextInt();
	}
	
	private static int askForTypeOfVehicle (Scanner scn) {
		int option = 0;
		
		System.out.println("What type of vehicle do you want to create?");
		do {
			option = inputInteger(scn, "Car(1)/Bike(2)/Tricicle(3)");
		} while (option < 1 || option > 3);	
		
		return option;
	}
	
	public static Vehicle createVehicle (Scanner scn, int type){
		String plate = plate(scn);
		String brand = input(scn, "Write the brand: ");
		String color = input(scn, "Write the color: ");
					
		switch(type) {
		case CAR: return new Car(plate, brand, color);
		case BIKE: return new Bike(plate, brand, color);
		default: return new Tricicle(plate, brand, color);
		}
	}
	
	public static Car createCar (Scanner scn) {
		return (Car)createVehicle(scn, CAR);
	}
	
	private static Bike createBike (Scanner scn) {
		return (Bike)createVehicle(scn, BIKE);
	}
	
	private static Tricicle createTricicle (Scanner scn) {
		return (Tricicle)createVehicle(scn, TRICICLE);
	}
	
	private static String plate (Scanner scn) {
		String plate = input(scn, "Write the plate: ");
		
		return plate.toUpperCase();
	}

	private static List<Wheel> askForFrontWheels (Scanner scn, Vehicle vehicle) {
		if (vehicle instanceof Car) 
			return addTwoWheels(scn);
		
		else return addOneWheel(scn);

	}
	
	private static List<Wheel> askForBackWheels (Scanner scn, Vehicle vehicle) {
		if (vehicle instanceof Bike) 	
			return addOneWheel(scn);
		
		else return addTwoWheels(scn);
	}
	
	private static List<Wheel> addTwoWheels(Scanner scn) {	
		return addXWheels(scn, 2);
	}
	
	
	private static List<Wheel> addOneWheel (Scanner scn) {
		return addXWheels (scn, 1);
	}
	
	private static List<Wheel> addXWheels (Scanner scn, int numberOfWheels) {
		List<Wheel> listOfWheels = new ArrayList<Wheel>();
		while (numberOfWheels > 0) {
			listOfWheels.add(createOneWheel(scn));
			numberOfWheels --;
		}
		return listOfWheels;
	}

 	private static Wheel createOneWheel (Scanner scn) {
		System.out.print("Put the wheel diamter: ");
		
		double wheelDiameter = scn.nextDouble();
		
		String wheelBrand = input(scn, "Write the wheel brand: ");
		Wheel wheel = new Wheel(wheelBrand, wheelDiameter);
		
		if (wheel.checkDiameter()) return wheel; 
		
		System.exit(0);
		return null;
 	}

	private static void checkWheels (Vehicle vehicle, List<Wheel>frontWheels, List<Wheel>backWheels) {
		if ((vehicle instanceof Car) && !checkWheels((Car)vehicle, frontWheels, backWheels)) {
			System.exit(0);
		}
		else if ((vehicle instanceof Bike) && !checkWheels((Bike)vehicle, frontWheels, backWheels)){
			System.exit(0);
		}
		else if ((vehicle instanceof Tricicle) && !checkWheels((Tricicle)vehicle, frontWheels, backWheels)){
			System.exit(0);
		}
	}
	
	private static boolean checkWheels (Car car, List<Wheel>frontWheels, List<Wheel>backWheels) {
		try{
			car.addWheels(frontWheels, backWheels);
		} catch(Exception e) {
			System.out.println("We are having errors with your wheels.");
			return false;
		}
		return true;
	}
	
	private static boolean checkWheels (Bike bike, List<Wheel>frontWheels, List<Wheel>backWheels) {
		try {
			bike.addTwoWheels(frontWheels, frontWheels);
		} catch(Exception e) {
			System.out.println("We are having errors with your wheels.");
			return false;
		}
		return true;
	}
	
	private static boolean checkWheels (Tricicle tricicle, List<Wheel>frontWheels, List<Wheel>backWheels) {
		try {
			tricicle.addThreeWheels(frontWheels, backWheels);
		} catch(Exception e) {
			System.out.println("We are having errors with your wheels.");
			return false;
		}
		return true;	
	}	
}

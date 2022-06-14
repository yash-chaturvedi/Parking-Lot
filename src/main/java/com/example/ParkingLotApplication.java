package com.example;

import com.example.model.ParkingLot;
import com.example.model.Vehicle;
import com.example.service.ParkingLotService;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author Yash Chaturvedi
 */
public class ParkingLotApplication {

    private static final ParkingLotService parkingLotService = new ParkingLotService();

    public static void main(String[] args) {

        ParkingLot parkingLot = null;
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println();
            String[] tokens = input.split(" ");

            String command = tokens[0];
            switch (command) {
                case "exit" :
                    return;
                case "create_parking_lot" :
                    parkingLot = parkingLotService.createParkingLot(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
                    break;
                case "park_vehicle" :
                   handleParkRequest(parkingLot, tokens[1], tokens[2], tokens[3]);
                   break;
                case "unpark_vehicle" :
                    handleUnparkRequest(parkingLot, tokens[1]);
                    break;
                case "display":
                    handleDisplayRequest(parkingLot, tokens[1], tokens[2]);
            }

        }
    }

    private static void handleDisplayRequest(ParkingLot parkingLot, String displayType, String vehicleType) {
        Vehicle.Type type = Vehicle.Type.valueOf(vehicleType);
        switch (displayType) {
            case "free_count" :
                parkingLotService.displayFreeCount(parkingLot, type);
                break;
            case "free_slots" :
                parkingLotService.displayFreeSlots(parkingLot, type);
                break;
            case "occupied_slots" :
                parkingLotService.displayOccupiedSlots(parkingLot, type);
                break;
        }
    }

    private static void handleUnparkRequest(ParkingLot parkingLot, String ticketId) {
        parkingLotService.unParkVehicle(parkingLot, ticketId);
    }

    private static void handleParkRequest(ParkingLot parkingLot, String vehicleType, String registrationNumber,
                                          String color) {
        Vehicle vehicle = new Vehicle(Vehicle.Type.valueOf(vehicleType), registrationNumber, color);
        if(Objects.nonNull(parkingLot)) {
            parkingLotService.parkVehicle(parkingLot, vehicle);
        }
        else {
            System.out.print("Please create a parking lot first");
        }
    }
}

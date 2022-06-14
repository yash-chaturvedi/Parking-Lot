package com.example.service;


import com.example.model.Floor;
import com.example.model.ParkingLot;
import com.example.model.Slot;
import com.example.model.Ticket;
import com.example.model.Vehicle;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Yash Chaturvedi
 */
public class ParkingLotService {

    public ParkingLot createParkingLot(String id, int numFloors, int numSlotsPerFloor) {
        ParkingLot parkingLot = new ParkingLot(id);
        for(int i=1; i<=numFloors; i++) {
            Floor floor = new Floor(i, numSlotsPerFloor);
            for(int j=1; j<=numSlotsPerFloor; j++) {
                Vehicle.Type vehicleType = getVehicleTypeFromSlotNumber(j);
                floor.addSlot(new Slot(vehicleType, j));
            }
            parkingLot.addFloor(floor);
        }
        System.out.println("Created parking lot with " + numFloors + " floors and " + numSlotsPerFloor + " slots per floor");
        return parkingLot;
    }

    private Vehicle.Type getVehicleTypeFromSlotNumber(int slotNumber) {
        if(slotNumber > 3) {
            return Vehicle.Type.CAR;
        }
        else if(slotNumber > 1) {
            return Vehicle.Type.BIKE;
        }
        else {
            return Vehicle.Type.TRUCK;
        }
    }

    public void parkVehicle(ParkingLot parkingLot, Vehicle vehicle) {
        for(Floor floor : parkingLot.getFloors()) {
            Slot slot = checkForEmptySlot(floor, vehicle.getType());
            if(Objects.nonNull(slot)) {
                Ticket ticket = createTicket(parkingLot.getId(), floor.getFloorNumber(), slot.getSlotNumber());
                slot.setTicket(ticket);
                slot.setVehicle(vehicle);
                System.out.println("Parked vehicle. Ticket ID: " + ticket.getTicketId());
                return;
            }
        }
        System.out.println("Parking Lot Full");
    }

    private Ticket createTicket(String parkingLotId, int floorNumber, int slotNumber) {
        return new Ticket(parkingLotId + "_" + floorNumber + "_" + slotNumber);
    }

    private Slot checkForEmptySlot(Floor floor, Vehicle.Type type) {
        List<Slot> slots = floor.getSlots();

        if(Vehicle.Type.TRUCK.equals(type)) {
            if(slots.get(0).isEmpty()) {
                return slots.get(0);
            }
        }
        else if(Vehicle.Type.BIKE.equals(type)) {
            for(int i=1; i<3; i++) {
                Slot slot = slots.get(i);
                if(slot.isEmpty()) {
                    return slot;
                }
            }
        }
        else if(Vehicle.Type.CAR.equals(type)) {
            for(int i=3; i<slots.size(); i++) {
                Slot slot = slots.get(i);
                if(slot.isEmpty()) {
                    return slot;
                }
            }
        }
        return null;
    }

    public void unParkVehicle(ParkingLot parkingLot, String ticketId) {
        if(!validateTicket(parkingLot, ticketId)) {
            System.out.println("Invalid Ticket");
            return;
        }
        String[] tokens = ticketId.split("_");
        int floorNumber = Integer.parseInt(tokens[1]);
        int slotNumber = Integer.parseInt(tokens[2]);
        Slot slot = parkingLot.getFloors().get(floorNumber - 1).getSlots().get(slotNumber - 1);
        Vehicle vehicle = slot.getVehicle();
        System.out.println("Unparked vehicle with Registration Number: " + vehicle.getRegistrationNumber() +
                " and Color: " + vehicle.getColor());
        slot.setVehicle(null);
        slot.setTicket(null);
    }

    private boolean validateTicket(ParkingLot parkingLot, String ticketId) {
        String[] tokens = ticketId.split("_");
        if(tokens.length != 3) {
            return false;
        }
        String floor = tokens[1];
        String slot = tokens[2];
        if(checkIfValidInteger(floor) && checkIfValidInteger(slot)) {
            int floorNumber = Integer.parseInt(floor);
            if(floorNumber <= parkingLot.getFloors().size()) {
                Floor curFloor = parkingLot.getFloors().get(floorNumber - 1);
                int slotNumber = Integer.parseInt(slot);
                if(slotNumber <= curFloor.getSlots().size()) {
                    return !curFloor.getSlots().get(slotNumber -1).isEmpty();
                }
            }
        }
        return false;
    }

    private boolean checkIfValidInteger(String str) {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

    public void displayFreeCount(ParkingLot parkingLot, Vehicle.Type vehicleType) {
        for(Floor floor : parkingLot.getFloors()) {
            int cnt = 0;
            for(Slot slot : floor.getSlots()) {
                if(slot.getVehicleType().equals(vehicleType)) {
                    cnt += slot.isEmpty() ? 1 : 0;
                }
            }
            System.out.println("No. of free slots for " + vehicleType.name() + " on Floor " +
                    floor.getFloorNumber() + " : " + cnt);
        }
    }

    public void displayFreeSlots(ParkingLot parkingLot, Vehicle.Type vehicleType) {
        for(Floor floor : parkingLot.getFloors()) {
            List<String> emptySlots = new ArrayList<>();
            for(Slot slot : floor.getSlots()) {
                if(slot.getVehicleType().equals(vehicleType)) {
                    if(slot.isEmpty()) {
                        emptySlots.add(String.valueOf(slot.getSlotNumber()));
                    }
                }
            }
            System.out.println("Free slots for " + vehicleType.name() +" on Floor " + floor.getFloorNumber() +
                    " : " + String.join(",", emptySlots));
        }
    }

    public void displayOccupiedSlots(ParkingLot parkingLot, Vehicle.Type vehicleType) {
        for(Floor floor : parkingLot.getFloors()) {
            List<String> occupiedSlots = new ArrayList<>();
            for(Slot slot : floor.getSlots()) {
                if(slot.getVehicleType().equals(vehicleType)) {
                    if(!slot.isEmpty()) {
                        occupiedSlots.add(String.valueOf(slot.getSlotNumber()));
                    }
                }
            }
            System.out.println("Occupied slots for " + vehicleType.name() +" on Floor " + floor.getFloorNumber() +
                    " : " + String.join(",", occupiedSlots));
        }
    }

}

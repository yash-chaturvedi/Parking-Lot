package com.example.model;

import java.util.Objects;

/**
 * @author Yash Chaturvedi
 */
public class Slot {

    private final int slotNumber;
    private Vehicle.Type vehicleType;
    private Ticket ticket;
    private Vehicle vehicle;

    public Slot(Vehicle.Type vehicleType, int slotNumber) {
        this.vehicleType = vehicleType;
        this.slotNumber = slotNumber;
    }

    public Vehicle.Type getVehicleType() {
        return vehicleType;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicleType(Vehicle.Type vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isEmpty() {
        return Objects.isNull(ticket);
    }
}

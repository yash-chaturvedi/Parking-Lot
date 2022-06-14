package com.example.model;

/**
 * @author Yash Chaturvedi
 */
public class Vehicle {

    public enum Type{
        CAR, BIKE, TRUCK;
    }

    private final Type type;
    private final String registrationNumber;
    private final String color;

    public Vehicle(Type type, String registrationNumber, String color) {
        this.type = type;
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public Type getType() {
        return type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }
}

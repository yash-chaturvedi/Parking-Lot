package com.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yash Chaturvedi
 */
public class ParkingLot {

    private final String id;
    private final List<Floor> floors;

    public ParkingLot(String id) {
        this.id = id;
        this.floors = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void addFloor(Floor floor) {
        this.floors.add(floor);
    }
}

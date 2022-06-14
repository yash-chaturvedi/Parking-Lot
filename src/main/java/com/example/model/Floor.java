package com.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yash Chaturvedi
 */
public class Floor {

    private final int floorNumber;
    private final List<Slot> slots;
    private final int maxNumberOfSlots;

    public Floor(int floorNumber, int maxNumberOfSlots) {
        this.floorNumber = floorNumber;
        this.maxNumberOfSlots = maxNumberOfSlots;
        this.slots = new ArrayList<>();
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public int getMaxNumberOfSlots() {
        return maxNumberOfSlots;
    }

    public void addSlot(Slot slot) {
        this.slots.add(slot);
    }
}

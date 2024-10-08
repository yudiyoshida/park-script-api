package com.example.parkscript.app.parking_spot.domain.entities;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ParkingSpot {
    private UUID id;
    private String name;
    private ParkingSpotType type;
    private boolean isOccupied;

    public ParkingSpot(String name, ParkingSpotType type, boolean isOccupied) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.type = type;
        this.isOccupied = isOccupied;
    }
}

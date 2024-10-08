package com.example.parkscript.app.parking_spot.dtos;

import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpot;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ParkingSpotDto {
    private final UUID id;
    private final String name;
    private final String type;
    private final boolean isOccupied;

    public ParkingSpotDto(ParkingSpot parkingSpot) {
        this.id = parkingSpot.getId();
        this.name = parkingSpot.getName();
        this.type = parkingSpot.getType().toString();
        this.isOccupied = parkingSpot.isOccupied();
    }
}

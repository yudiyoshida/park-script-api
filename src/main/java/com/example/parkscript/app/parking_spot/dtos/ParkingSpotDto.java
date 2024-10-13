package com.example.parkscript.app.parking_spot.dtos;

import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpot;
import com.example.parkscript.app.vehicle.dtos.VehicleDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ParkingSpotDto {
    private final String id;
    private final String name;
    private final String type;
    private final VehicleDto vehicle;
    private final LocalDateTime occupiedAt;

    public ParkingSpotDto(ParkingSpot parkingSpot) {
        this.id = parkingSpot.getId();
        this.name = parkingSpot.getName();
        this.type = parkingSpot.getType().toString();
        this.vehicle = parkingSpot.getVehicle() != null ? new VehicleDto(parkingSpot.getVehicle()) : null;
        this.occupiedAt = parkingSpot.getOccupiedAt();
    }
}

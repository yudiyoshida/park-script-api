package com.example.parkscript.app.parking_spot.dtos;

import com.example.parkscript.app.parking.dtos.ParkingDto;
import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpot;
import lombok.Getter;

import java.util.List;

@Getter
public class ParkingSpotDto {
    private final String id;
    private final String name;
    private final String type;
    private final boolean isOccupied;
    private final List<ParkingDto> parkings;

    public ParkingSpotDto(ParkingSpot parkingSpot) {
        this.id = parkingSpot.getId();
        this.name = parkingSpot.getName();
        this.type = parkingSpot.getType().toString();
        this.isOccupied = parkingSpot.isOccupied();
        this.parkings = parkingSpot.getParkings().stream().map(ParkingDto::new).toList();
    }
}

package com.example.parkscript.app.parking.dtos;

import com.example.parkscript.app.parking.domain.entities.Parking;
import com.example.parkscript.app.vehicle.dtos.VehicleDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ParkingDto {
    private final String id;
    private final LocalDateTime occupiedAt;
    private final LocalDateTime vacatedAt;
    private final VehicleDto vehicle;

    public ParkingDto(Parking parking) {
        this.id = parking.getId();
        this.occupiedAt = parking.getOccupiedAt();
        this.vacatedAt = parking.getVacatedAt();
        this.vehicle = new VehicleDto(parking.getVehicle());
    }
}

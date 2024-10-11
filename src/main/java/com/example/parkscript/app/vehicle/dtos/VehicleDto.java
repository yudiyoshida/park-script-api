package com.example.parkscript.app.vehicle.dtos;

import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import lombok.Getter;

@Getter
public class VehicleDto {
    private final String id;
    private final String plate;
    private final String model;
    private final String color;

    public VehicleDto(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.plate = vehicle.getPlate();
        this.model = vehicle.getModel();
        this.color = vehicle.getColor();
    }
}

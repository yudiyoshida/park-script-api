package com.example.parkscript.app.vehicle.domain.entities;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Vehicle {
    private UUID id;
    private String plate;
    private String model;
    private String color;

    public Vehicle(String plate, String model, String color) {
        this.id = UUID.randomUUID();
        this.plate = plate;
        this.model = model;
        this.color = color;
    }
}

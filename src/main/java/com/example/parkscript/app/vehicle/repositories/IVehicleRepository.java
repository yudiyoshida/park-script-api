package com.example.parkscript.app.vehicle.repositories;

import com.example.parkscript.app.vehicle.domain.entities.Vehicle;

import java.util.Optional;

public interface IVehicleRepository {
    Optional<Vehicle> findByPlate(String plate);
}

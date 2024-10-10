package com.example.parkscript.app.vehicle.repositories.adapters;

import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import com.example.parkscript.app.vehicle.repositories.IVehicleRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VehicleInMemoryRepository implements IVehicleRepository {
    private final List<Vehicle> vehicles = new ArrayList<>();

    @Override
    public Optional<Vehicle> findByPlate(String plate) {
        return this.vehicles.stream()
                .filter(vehicle -> vehicle.getPlate().equals(plate))
                .findFirst();
    }
}

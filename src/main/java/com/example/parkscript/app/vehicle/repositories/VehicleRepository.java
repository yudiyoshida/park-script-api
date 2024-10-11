package com.example.parkscript.app.vehicle.repositories;

import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Optional<Vehicle> findByPlate(String plate);
}

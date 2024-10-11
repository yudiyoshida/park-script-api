package com.example.parkscript.app.parking_spot.repositories;

import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingVehicleRepository extends JpaRepository<ParkingSpot, String> { }

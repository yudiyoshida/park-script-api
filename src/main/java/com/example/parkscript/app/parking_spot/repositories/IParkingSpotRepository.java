package com.example.parkscript.app.parking_spot.repositories;

import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpot;

import java.util.List;

public interface IParkingSpotRepository {
    List<ParkingSpot> findAll();
}

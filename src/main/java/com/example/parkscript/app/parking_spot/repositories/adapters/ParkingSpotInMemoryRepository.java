package com.example.parkscript.app.parking_spot.repositories.adapters;

import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpotType;
import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpot;
import com.example.parkscript.app.parking_spot.repositories.IParkingSpotRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ParkingSpotInMemoryRepository implements IParkingSpotRepository {
    private final List<ParkingSpot> parkingSpots = new ArrayList<>();

    public ParkingSpotInMemoryRepository() {
        this.parkingSpots.add(new ParkingSpot("A1", ParkingSpotType.HANDICAPPED, false));
        this.parkingSpots.add(new ParkingSpot("A2", ParkingSpotType.HANDICAPPED, false));
        this.parkingSpots.add(new ParkingSpot("A3", ParkingSpotType.ELDERLY, false));
        this.parkingSpots.add(new ParkingSpot("A4", ParkingSpotType.ELDERLY, false));
        this.parkingSpots.add(new ParkingSpot("A5", ParkingSpotType.ELDERLY, false));
        this.parkingSpots.add(new ParkingSpot("A6", ParkingSpotType.NORMAL, false));
        this.parkingSpots.add(new ParkingSpot("A7", ParkingSpotType.NORMAL, false));
        this.parkingSpots.add(new ParkingSpot("A8", ParkingSpotType.NORMAL, false));
        this.parkingSpots.add(new ParkingSpot("A9", ParkingSpotType.NORMAL, false));
        this.parkingSpots.add(new ParkingSpot("A10", ParkingSpotType.NORMAL, false));
    }

    @Override
    public List<ParkingSpot> findAll() {
        return this.parkingSpots;
    }
}

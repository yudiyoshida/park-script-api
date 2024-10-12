package com.example.parkscript.app.parking_spot.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parking_spots")
@Getter
@NoArgsConstructor
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ParkingSpotType type;

    @Column(nullable = false)
    private boolean isOccupied;

    public ParkingSpot(String name, ParkingSpotType type, boolean isOccupied) {
        this.name = name;
        this.type = type;
        this.isOccupied = isOccupied;
    }

    public void parkVehicle() {
        this.isOccupied = true;
    }
}

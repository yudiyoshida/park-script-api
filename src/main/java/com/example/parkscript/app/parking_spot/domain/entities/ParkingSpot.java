package com.example.parkscript.app.parking_spot.domain.entities;

import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @OneToOne
    private Vehicle vehicle;

    @Column(nullable = true)
    private LocalDateTime occupiedAt;

    public ParkingSpot(String name, ParkingSpotType type) {
        this.name = name;
        this.type = type;
    }

    public boolean isOccupied() {
        return this.vehicle != null;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.occupiedAt = LocalDateTime.now();
    }
}

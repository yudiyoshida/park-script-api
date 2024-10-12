package com.example.parkscript.app.parking.domain.entities;

import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpot;
import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "parkings")
@Getter
@NoArgsConstructor
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "occupied_at", nullable = false)
    private LocalDateTime occupiedAt;

    @Column(name = "vacated_at")
    private LocalDateTime vacatedAt;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "parking_spot_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ParkingSpot parkingSpot;

    public Parking(Vehicle vehicle, ParkingSpot parkingSpot) {
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.occupiedAt = LocalDateTime.now();
    }
}

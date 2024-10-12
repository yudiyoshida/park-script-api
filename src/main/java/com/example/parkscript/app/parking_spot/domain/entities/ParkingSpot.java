package com.example.parkscript.app.parking_spot.domain.entities;

import com.example.parkscript.app.parking.domain.entities.Parking;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    @OneToMany(mappedBy = "parkingSpot")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Parking> parkings;

    public ParkingSpot(String name, ParkingSpotType type, boolean isOccupied) {
        this.name = name;
        this.type = type;
        this.isOccupied = isOccupied;
    }

    public void parkVehicle() {
        this.isOccupied = true;
    }
}

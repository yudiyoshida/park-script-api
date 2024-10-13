package com.example.parkscript.app.vehicle.domain.entities;

import com.example.parkscript.app.client.domain.entities.Client;
import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicles")
@Getter
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String plate;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String color;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToOne(mappedBy = "vehicle")
    @JoinColumn(name = "parking_spot_id")
    private ParkingSpot parkingSpot;

    public Vehicle(String plate, String model, String color, Client client) {
        this.plate = plate;
        this.model = model;
        this.color = color;
        this.client = client;
    }

    public Vehicle(String id, String plate, String model, String color, Client client) {
        this.id = id;
        this.plate = plate;
        this.model = model;
        this.color = color;
        this.client = client;
    }

    public boolean isParked() {
        return this.parkingSpot != null;
    }
}

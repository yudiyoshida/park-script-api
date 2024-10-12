package com.example.parkscript.app.vehicle.domain.entities;

import com.example.parkscript.app.parking.domain.entities.Parking;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    @OneToMany(mappedBy = "vehicle")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Parking> parkings;

    public Vehicle(String plate, String model, String color) {
        this.plate = plate;
        this.model = model;
        this.color = color;
    }

    public Vehicle(String id, String plate, String model, String color) {
        this.id = id;
        this.plate = plate;
        this.model = model;
        this.color = color;
    }

    public boolean isParked() {
        var parking = this.getParkings().stream()
                .filter(ps -> ps.getVehicle() == this)
                .filter(ps -> ps.getVacatedAt() == null)
                .findFirst();

        return parking.isPresent();
    }
}

package com.example.parkscript.app.parking.domain.entities;

import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "parkings")
@Getter
public class Parking {
    private static final Double FIRST_HOUR_VALUE = 6.0;
    private static final Double HOUR_VALUE = 3.0;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "client_cpf", nullable = false)
    private String clientCpf;

    @Column(name = "vehicle_plate", nullable = false)
    private String vehiclePlate;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "final_time", nullable = false)
    private LocalDateTime finalTime;

    @Column(nullable = false)
    private Double amount;

    public Parking(Vehicle vehicle, LocalDateTime occupiedAt) {
        this.clientName = vehicle.getClient().getName();
        this.clientCpf = vehicle.getClient().getCpf();
        this.vehiclePlate = vehicle.getPlate();
        this.startTime = occupiedAt;
        this.finalTime = LocalDateTime.now();
        this.amount = this.calculateAmount();
    }

    private Double calculateAmount() {
        var hours = this.startTime.until(this.finalTime, ChronoUnit.HOURS);

        if (hours == 0) {
            return FIRST_HOUR_VALUE;
        }
        return FIRST_HOUR_VALUE + ((hours - 1) * HOUR_VALUE);
    }
}

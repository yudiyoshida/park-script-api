package com.example.parkscript.app.parking_spot.domain.entities;

import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class ParkingSpotTest {
    private ParkingSpot parkingSpot;

    @BeforeEach
    void setUp() {
        this.parkingSpot = new ParkingSpot("A1", ParkingSpotType.NORMAL);
    }

    @Test
    void isOccupied_case01() {
        this.parkingSpot.releaseVehicle();

        assertThat(this.parkingSpot.isOccupied()).isFalse();
    }

    @Test
    void isOccupied_case02() {
        this.parkingSpot.parkVehicle(Mockito.mock(Vehicle.class));

        assertThat(this.parkingSpot.isOccupied()).isTrue();
    }

    @Test
    void parkVehicle() {
        var vehicle = Mockito.mock(Vehicle.class);
        this.parkingSpot.parkVehicle(vehicle);

        assertThat(this.parkingSpot.getVehicle()).isEqualTo(vehicle);
        assertThat(this.parkingSpot.getOccupiedAt()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
    }

    @Test
    void releaseVehicle() {
        this.parkingSpot.releaseVehicle();

        assertThat(this.parkingSpot.getVehicle()).isNull();
        assertThat(this.parkingSpot.getOccupiedAt()).isNull();
    }
}
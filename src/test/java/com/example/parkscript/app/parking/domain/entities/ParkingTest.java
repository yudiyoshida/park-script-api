package com.example.parkscript.app.parking.domain.entities;

import com.example.parkscript.app.client.domain.entities.Client;
import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ParkingTest {
    @Autowired
    private Parking parking;

    @Test
    void shouldReturnSixWhenParkingTimeIsZero() {
        var client = Mockito.mock(Client.class);
        var vehicle = new Vehicle("John Doe", "12345678901", "ABC1234", client);
        var occupiedAt = LocalDateTime.now();
        var parking = new Parking(vehicle, occupiedAt);

        assertEquals(6.0, parking.getAmount());
    }

    @Test
    void shouldReturnSixWhenParkingTimeIsOneHour() {
        var client = Mockito.mock(Client.class);
        var vehicle = new Vehicle("John Doe", "12345678901", "ABC1234", client);
        var occupiedAt = LocalDateTime.now().minusHours(1);
        var parking = new Parking(vehicle, occupiedAt);

        assertEquals(6.0, parking.getAmount());
    }

    @Test
    void shouldReturnNineWhenParkingTimeIsTwoHours() {
        var client = Mockito.mock(Client.class);
        var vehicle = new Vehicle("John Doe", "12345678901", "ABC1234", client);
        var occupiedAt = LocalDateTime.now().minusHours(2);
        var parking = new Parking(vehicle, occupiedAt);

        assertEquals(9.0, parking.getAmount());
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 24, 48, 156})
    void shouldReturnHoursXthree(int hours) {
        var firstHourValue = 6.0;
        var hourValue = 3.0;
        var amount = ((hours - 1) * hourValue) + firstHourValue;

        var client = Mockito.mock(Client.class);
        var vehicle = new Vehicle("John Doe", "12345678901", "ABC1234", client);
        var occupiedAt = LocalDateTime.now().minusHours(hours);
        var parking = new Parking(vehicle, occupiedAt);

        assertEquals(amount, parking.getAmount());
    }
}
package com.example.parkscript.app.parking_spot.usecases.park_vehicle;

import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpot;
import com.example.parkscript.app.parking_spot.repositories.ParkingSpotRepository;
import com.example.parkscript.app.parking_spot.usecases.park_vehicle.dtos.ParkVehicleInputDto;
import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import com.example.parkscript.app.vehicle.repositories.VehicleRepository;
import com.example.parkscript.shared.errors.AppException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkVehicleUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private ParkingSpotRepository parkingSpotRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private ParkVehicleUseCase parkVehicleUseCase;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    void shouldThrowAnErrorIfVehicleIsNotFound() {
        Mockito.when(this.vehicleRepository.findById("v-id")).thenReturn(Optional.empty());

        var excetion = assertThrows(AppException.class, () -> {
            this.parkVehicleUseCase.execute(new ParkVehicleInputDto("ps-id","v-id"));
        });

        assertEquals("Veículo não encontrado", excetion.getMessage());
    }

    @Test
    void shouldThrowAnErrorIfParkingSpotIsNotFound() {
        var vehicle = Mockito.mock(Vehicle.class);
        Mockito.when(this.vehicleRepository.findById("v-id")).thenReturn(Optional.of(vehicle));
        Mockito.when(this.parkingSpotRepository.findById("ps-id")).thenReturn(Optional.empty());

        var exception = assertThrows(AppException.class, () -> {
            this.parkVehicleUseCase.execute((new ParkVehicleInputDto("ps-id", "v-id")));
        });

        assertEquals("Vaga não encontrada", exception.getMessage());
    }

    @Test
    void shouldThrowAnErrorIfParkingSpotIsOccupied() {
        var vehicle = Mockito.mock(Vehicle.class);
        var parkingSpot = Mockito.mock(ParkingSpot.class);
        Mockito.when(this.vehicleRepository.findById("v-id")).thenReturn(Optional.of(vehicle));
        Mockito.when(this.parkingSpotRepository.findById("ps-id")).thenReturn(Optional.of(parkingSpot));
        Mockito.when(parkingSpot.isOccupied()).thenReturn(true);

        var exception = assertThrows(AppException.class, () -> {
            this.parkVehicleUseCase.execute((new ParkVehicleInputDto("ps-id", "v-id")));
        });

        assertEquals("Vaga de estacionamento já ocupada", exception.getMessage());
    }

    @Test
    void shouldThrowAnErrorIfVehicleIsParked() {
        var vehicle = Mockito.mock(Vehicle.class);
        var parkingSpot = Mockito.mock(ParkingSpot.class);
        Mockito.when(this.vehicleRepository.findById("v-id")).thenReturn(Optional.of(vehicle));
        Mockito.when(this.parkingSpotRepository.findById("ps-id")).thenReturn(Optional.of(parkingSpot));
        Mockito.when(parkingSpot.isOccupied()).thenReturn(false);
        Mockito.when(vehicle.isParked()).thenReturn(true);

        var exception = assertThrows(AppException.class, () -> {
            this.parkVehicleUseCase.execute((new ParkVehicleInputDto("ps-id", "v-id")));
        });

        assertEquals("Veículo já está estacionado", exception.getMessage());
    }
}
package com.example.parkscript.app.parking.usecases.park_vehicle.dtos;

import com.example.parkscript.app.parking.usecases.park_vehicle.ParkVehicleUseCase;
import com.example.parkscript.app.parking.repositories.ParkingRepository;
import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpot;
import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpotType;
import com.example.parkscript.app.parking_spot.repositories.ParkingSpotRepository;
import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import com.example.parkscript.app.vehicle.repositories.VehicleRepository;
import com.example.parkscript.shared.errors.AppException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ParkVehicleUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private ParkingSpotRepository parkingSpotRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private ParkingRepository parkingRepository;

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

        var ex = assertThrows(AppException.class, () -> {
            this.parkVehicleUseCase.execute(new ParkVehicleInputDto("ps-id", "v-id"));
        });

        assertEquals("Veículo não encontrado", ex.getMessage());
    }

    @Test
    void shouldThrowAnErrorIfParkingSpotIsNotFound() {
        var vehicle = Mockito.mock(Vehicle.class);
        Mockito.when(this.vehicleRepository.findById("v-id")).thenReturn(Optional.of(vehicle));
        Mockito.when(this.parkingSpotRepository.findById("ps-id")).thenReturn(Optional.empty());

        var ex = assertThrows(AppException.class, () -> {
            this.parkVehicleUseCase.execute(new ParkVehicleInputDto("ps-id", "v-id"));
        });

        assertEquals("Vaga não encontrada", ex.getMessage());
    }

    @Test
    void shouldThrowAnErrorIfParkingSpotIsOccupied() {
        var vehicle = Mockito.mock(Vehicle.class);
        var parkingSpot = new ParkingSpot("A1", ParkingSpotType.NORMAL, true);

        Mockito.when(this.vehicleRepository.findById("v-id")).thenReturn(Optional.of(vehicle));
        Mockito.when(this.parkingSpotRepository.findById("ps-id")).thenReturn(Optional.of(parkingSpot));

        var ex = assertThrows(AppException.class, () -> {
            this.parkVehicleUseCase.execute(new ParkVehicleInputDto("ps-id", "v-id"));
        });

        assertEquals("Vaga de estacionamento já ocupada", ex.getMessage());
    }
    @Test
    void shouldThrowAnErrorIfVehicleIsAlreadyParked() {
        var vehicle = Mockito.mock(Vehicle.class);
        var parkingSpot = Mockito.mock(ParkingSpot.class);

        Mockito.when(this.vehicleRepository.findById("v-id")).thenReturn(Optional.of(vehicle));
        Mockito.when(this.parkingSpotRepository.findById("ps-id")).thenReturn(Optional.of(parkingSpot));
        Mockito.when(vehicle.isParked()).thenReturn(true);

        var ex = assertThrows(AppException.class, () -> {
            this.parkVehicleUseCase.execute(new ParkVehicleInputDto("ps-id", "v-id"));
        });

        assertEquals("Veículo já está estacionado", ex.getMessage());
    }
}
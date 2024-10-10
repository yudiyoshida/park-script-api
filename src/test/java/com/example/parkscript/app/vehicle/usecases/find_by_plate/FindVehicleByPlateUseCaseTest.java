package com.example.parkscript.app.vehicle.usecases.find_by_plate;

import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import com.example.parkscript.app.vehicle.dtos.VehicleDto;
import com.example.parkscript.app.vehicle.repositories.IVehicleRepository;
import com.example.parkscript.shared.errors.AppException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class FindVehicleByPlateUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private IVehicleRepository vehicleRepository;

    @InjectMocks
    private FindVehicleByPlateUseCase findVehicleByPlateUseCase;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    void shouldThrowAnErrorWhenVehicleIsNotFound() {
        Mockito.when(this.vehicleRepository.findByPlate("ABC1234")).thenReturn(Optional.empty());

        var exception = assertThrows(
            AppException.class,
            () -> this.findVehicleByPlateUseCase.execute("ABC1234")
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Veículo não encontrado", exception.getMessage());
    }

    @Test
    void shouldReturnVehicleDtoWhenVehicleIsFound() {
        var vehicle = new Vehicle("ABC1234", "Fiat", "Uno");
        Mockito.when(this.vehicleRepository.findByPlate("ABC1234")).thenReturn(Optional.of(vehicle));

        var result = this.findVehicleByPlateUseCase.execute("ABC1234");

        assertInstanceOf(VehicleDto.class, result);
        assertEquals("ABC1234", result.getPlate());
        assertEquals("Fiat", result.getModel());
        assertEquals("Uno", result.getColor());
    }
}
package com.example.parkscript.app.vehicle.usecases.create;

import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import com.example.parkscript.app.vehicle.repositories.VehicleRepository;
import com.example.parkscript.app.vehicle.usecases.create.dtos.CreateVehicleInputDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreateVehicleUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private CreateVehicleUseCase createVehicleUseCase;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    void shouldCreateVehicleWhenIsNotFoundByPlate() {
        // Given
        String plate = "ABC-1234";
        String color = "Preto";
        String model = "SUV";

        Mockito.when(this.vehicleRepository.findByPlate(plate)).thenReturn(Optional.empty());
        Mockito.when(this.vehicleRepository.save(ArgumentMatchers.any(Vehicle.class))).thenAnswer(answer -> answer.getArgument(0));


        // When
        ArgumentCaptor<Vehicle> captor = ArgumentCaptor.forClass(Vehicle.class);
        this.createVehicleUseCase.execute(new CreateVehicleInputDto(plate, color, model));

        // Then
        Mockito.verify(this.vehicleRepository).save(captor.capture());

        Vehicle vehicle = captor.getValue();
        assertNull(vehicle.getId());
        assertEquals(plate, vehicle.getPlate());
        assertEquals(color, vehicle.getColor());
        assertEquals(model, vehicle.getModel());
    }

    @Test
    void shouldUpdateVehicleWhenIsFoundByPlate() {
        // Given
        String id = "random-id";
        String plate = "ABC-1234";
        String color = "Preto";
        String model = "SUV";

        Vehicle vehicle = new Vehicle(id, plate, model, color);
        Mockito.when(this.vehicleRepository.findByPlate(plate)).thenReturn(Optional.of(vehicle));
        Mockito.when(this.vehicleRepository.save(ArgumentMatchers.any(Vehicle.class))).thenAnswer(answer -> answer.getArgument(0));

        // When
        ArgumentCaptor<Vehicle> captor = ArgumentCaptor.forClass(Vehicle.class);
        this.createVehicleUseCase.execute(new CreateVehicleInputDto(plate, color, model));

        // Then
        Mockito.verify(this.vehicleRepository).save(captor.capture());

        Vehicle updatedVehicle = captor.getValue();
        assertEquals(id, vehicle.getId());
        assertEquals(plate, vehicle.getPlate());
        assertEquals(color, vehicle.getColor());
        assertEquals(model, vehicle.getModel());
    }
}
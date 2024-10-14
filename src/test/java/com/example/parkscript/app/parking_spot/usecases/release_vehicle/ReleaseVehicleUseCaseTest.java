package com.example.parkscript.app.parking_spot.usecases.release_vehicle;

import com.example.parkscript.app.client.domain.entities.Client;
import com.example.parkscript.app.parking.repositories.ParkingRepository;
import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpot;
import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpotType;
import com.example.parkscript.app.parking_spot.repositories.ParkingSpotRepository;
import com.example.parkscript.app.parking_spot.usecases.release_vehicle.dtos.ReleaseVehicleInputDto;
import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import com.example.parkscript.shared.errors.AppException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReleaseVehicleUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private ParkingRepository parkingRepository;

    @Mock
    private ParkingSpotRepository parkingSpotRepository;

    @InjectMocks
    private ReleaseVehicleUseCase releaseVehicleUseCase;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    void shouldThrowAnErrorIfParkingSpotIsNotFound() {
        Mockito.when(this.parkingSpotRepository.findById("id")).thenReturn(Optional.empty());

        var ex = assertThrows(AppException.class, () -> {
            this.releaseVehicleUseCase.execute(new ReleaseVehicleInputDto("id"));
        });

        assertEquals("Vaga não encontrada", ex.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
    }

    @Test
    void shouldThrowAnErrorIfParkingSpotIsNotOccupied() {
        var parkingSpot = Mockito.mock(ParkingSpot.class);
        Mockito.when(this.parkingSpotRepository.findById("id")).thenReturn(Optional.of(parkingSpot));
        Mockito.when(parkingSpot.isOccupied()).thenReturn(false);

        var ex = assertThrows(AppException.class, () -> {
            this.releaseVehicleUseCase.execute(new ReleaseVehicleInputDto("id"));
        });

        assertEquals("A vaga já se encontra vazia", ex.getMessage());
    }

    @Test
    void shouldReleaseTheParkingSpot() {
        var client = Mockito.mock(Client.class);
        var vehicle = new Vehicle("abc-1234", "Fiat", "Uno", client);
        var parkingSpot = new ParkingSpot("A1", ParkingSpotType.HANDICAPPED);
        parkingSpot.parkVehicle(vehicle);

        Mockito.when(this.parkingSpotRepository.findById("id")).thenReturn(Optional.of(parkingSpot));

        ArgumentCaptor<ParkingSpot> argCaptor = ArgumentCaptor.forClass(ParkingSpot.class);
        this.releaseVehicleUseCase.execute(new ReleaseVehicleInputDto("id"));

        Mockito.verify(this.parkingSpotRepository).save(argCaptor.capture());

        var arguments = argCaptor.getValue();
        assertThat(arguments.getVehicle()).isNull();
        assertThat(arguments.getOccupiedAt()).isNull();
    }

    @Test
    void shouldRegisterTheParking() {
        var client = Mockito.mock(Client.class);
        var vehicle = new Vehicle("abc-1234", "Fiat", "Uno", client);
        var parkingSpot = new ParkingSpot("A1", ParkingSpotType.HANDICAPPED);
        parkingSpot.parkVehicle(vehicle);

        Mockito.when(this.parkingSpotRepository.findById("id")).thenReturn(Optional.of(parkingSpot));

        this.releaseVehicleUseCase.execute(new ReleaseVehicleInputDto("id"));
        Mockito.verify(this.parkingRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }
}
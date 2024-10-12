package com.example.parkscript.app.parking_spot.usecases.list_all;

import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpotType;
import com.example.parkscript.app.parking_spot.dtos.ParkingSpotDto;
import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpot;
import com.example.parkscript.app.parking_spot.repositories.ParkingSpotRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ListAllParkingSpotUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private ParkingSpotRepository parkingSpotRepository;

    @InjectMocks
    private ListAllParkingSpotUseCase listAllParkingSpotUseCase;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    void shouldReturnAListOfParkingSpotsDto() {
        // Given
        ParkingSpot parkingSpot = new ParkingSpot("name test", ParkingSpotType.HANDICAPPED, true);
        Mockito.when(this.parkingSpotRepository.findAll(ArgumentMatchers.any(Sort.class))).thenReturn(List.of(parkingSpot));

        // When
        var result = this.listAllParkingSpotUseCase.execute();

        // Then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isInstanceOf(ParkingSpotDto.class);
    }
}
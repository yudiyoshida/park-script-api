package com.example.parkscript.app.parking_spot.usecases.list_all.dtos;

import com.example.parkscript.app.parking_spot.dtos.ParkingSpotDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ListAllParkingSpotOutputDto {
    private final Integer occupiedSpots;
    private final Integer freeSpots;
    private final Integer totalSpots;
    private final List<ParkingSpotDto> parkingSpots;

    public ListAllParkingSpotOutputDto(List<ParkingSpotDto> parkingSpots) {
        this.parkingSpots = parkingSpots;
        this.occupiedSpots = (int) parkingSpots.stream().filter((ps) -> ps.getVehicle() != null).count();
        this.freeSpots = (int) parkingSpots.stream().filter((ps) -> ps.getVehicle() == null).count();
        this.totalSpots = parkingSpots.size();
    }
}

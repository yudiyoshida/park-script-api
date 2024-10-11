package com.example.parkscript.app.parking_spot.usecases.list_all;

import com.example.parkscript.app.parking_spot.dtos.ParkingSpotDto;
import com.example.parkscript.app.parking_spot.repositories.ParkingVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllParkingSpotUseCase {
    @Autowired
    private ParkingVehicleRepository parkingSpotRepository;

    public List<ParkingSpotDto> execute() {
        var result = this.parkingSpotRepository.findAll();

        return result.stream()
                .map(ParkingSpotDto::new)
                .toList();
    }
}

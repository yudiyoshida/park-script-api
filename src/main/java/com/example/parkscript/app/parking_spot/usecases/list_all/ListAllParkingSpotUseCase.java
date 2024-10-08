package com.example.parkscript.app.parking_spot.usecases.list_all;

import com.example.parkscript.app.parking_spot.dtos.ParkingSpotDto;
import com.example.parkscript.app.parking_spot.repositories.IParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllParkingSpotUseCase {
    @Autowired
    @Qualifier("parkingSpotInMemoryRepository")
    private IParkingSpotRepository parkingSpotRepository;

    public List<ParkingSpotDto> execute() {
        var result = this.parkingSpotRepository.findAll();

        return result.stream()
                .map(ParkingSpotDto::new)
                .toList();
    }
}

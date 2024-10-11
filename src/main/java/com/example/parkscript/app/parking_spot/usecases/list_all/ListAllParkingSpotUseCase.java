package com.example.parkscript.app.parking_spot.usecases.list_all;

import com.example.parkscript.app.parking_spot.dtos.ParkingSpotDto;
import com.example.parkscript.app.parking_spot.repositories.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllParkingSpotUseCase {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public List<ParkingSpotDto> execute() {
        var result = this.parkingSpotRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

        return result.stream()
                .map(ParkingSpotDto::new)
                .toList();
    }
}

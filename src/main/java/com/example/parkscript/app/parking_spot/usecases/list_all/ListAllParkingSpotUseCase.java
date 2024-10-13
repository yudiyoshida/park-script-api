package com.example.parkscript.app.parking_spot.usecases.list_all;

import com.example.parkscript.app.parking_spot.dtos.ParkingSpotDto;
import com.example.parkscript.app.parking_spot.repositories.ParkingSpotRepository;
import com.example.parkscript.app.parking_spot.usecases.list_all.dtos.ListAllParkingSpotOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllParkingSpotUseCase {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public ListAllParkingSpotOutputDto execute() {
        var result = this.parkingSpotRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

        List<ParkingSpotDto> parkingSpotDtos = result.stream().map(ParkingSpotDto::new).toList();

        return new ListAllParkingSpotOutputDto(parkingSpotDtos);
    }
}

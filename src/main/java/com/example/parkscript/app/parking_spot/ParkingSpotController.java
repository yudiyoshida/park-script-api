package com.example.parkscript.app.parking_spot;

import com.example.parkscript.app.parking_spot.dtos.ParkingSpotDto;
import com.example.parkscript.app.parking_spot.usecases.list_all.ListAllParkingSpotUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking-spots")
@RequiredArgsConstructor
public class ParkingSpotController {
    private final ListAllParkingSpotUseCase listAllParkingSpotUseCase;

    @GetMapping()
    public ResponseEntity<List<ParkingSpotDto>> listAll() {
        var result = this.listAllParkingSpotUseCase.execute();

        return ResponseEntity.ok(result);
    }
}

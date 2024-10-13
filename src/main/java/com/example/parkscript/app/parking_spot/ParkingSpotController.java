package com.example.parkscript.app.parking_spot;

import com.example.parkscript.app.parking_spot.dtos.ParkingSpotDto;
import com.example.parkscript.app.parking_spot.usecases.list_all.ListAllParkingSpotUseCase;
import com.example.parkscript.app.parking_spot.usecases.park_vehicle.ParkVehicleUseCase;
import com.example.parkscript.app.parking_spot.usecases.park_vehicle.dtos.ParkVehicleInputDto;
import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-spots")
@RequiredArgsConstructor
public class ParkingSpotController {
    private final ListAllParkingSpotUseCase listAllParkingSpotUseCase;
    private final ParkVehicleUseCase parkVehicleUseCase;

    @GetMapping()
    public ResponseEntity<List<ParkingSpotDto>> listAll() {
        var result = this.listAllParkingSpotUseCase.execute();

        return ResponseEntity.ok(result);
    }

    @PostMapping("/park")
    public ResponseEntity<Vehicle> parkVehicle(@RequestBody @Valid ParkVehicleInputDto body) {
        var result = this.parkVehicleUseCase.execute(body);

        return ResponseEntity.ok().body(result);
    }
}

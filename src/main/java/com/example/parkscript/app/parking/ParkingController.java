package com.example.parkscript.app.parking;

import com.example.parkscript.app.parking.usecases.park_vehicle.ParkVehicleUseCase;
import com.example.parkscript.app.parking.usecases.park_vehicle.dtos.ParkVehicleInputDto;
import com.example.parkscript.app.parking.usecases.park_vehicle.dtos.ParkVehicleOutputDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parkings")
@RequiredArgsConstructor
public class ParkingController {
    private final ParkVehicleUseCase parkVehicleUseCase;

    @PostMapping()
    public ResponseEntity<ParkVehicleOutputDto> parkVehicle(@RequestBody @Valid ParkVehicleInputDto body) {
        var result = this.parkVehicleUseCase.execute(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}

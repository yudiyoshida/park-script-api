package com.example.parkscript.app.vehicle;

import com.example.parkscript.app.vehicle.dtos.VehicleDto;
import com.example.parkscript.app.vehicle.usecases.find_by_plate.FindVehicleByPlateUseCase;
import com.example.parkscript.app.vehicle.usecases.find_by_plate.dtos.FindVehicleByPlateInputDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final FindVehicleByPlateUseCase findVehicleByPlateUseCase;

    @PostMapping("/find-by-plate")
    public ResponseEntity<VehicleDto> findByPlate(@RequestBody @Valid FindVehicleByPlateInputDto body) {
        var result = this.findVehicleByPlateUseCase.execute(body.plate());

        return ResponseEntity.ok(result);
    }
}

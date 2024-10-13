package com.example.parkscript.app.parking_spot;

import com.example.parkscript.app.parking_spot.dtos.ParkingSpotDto;
import com.example.parkscript.app.parking_spot.usecases.list_all.ListAllParkingSpotUseCase;
import com.example.parkscript.app.parking_spot.usecases.park_vehicle.ParkVehicleUseCase;
import com.example.parkscript.app.parking_spot.usecases.park_vehicle.dtos.ParkVehicleInputDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-spots")
@RequiredArgsConstructor
@Tag(name = "Vagas de estacionamento")
public class ParkingSpotController {
    private final ListAllParkingSpotUseCase listAllParkingSpotUseCase;
    private final ParkVehicleUseCase parkVehicleUseCase;

    @GetMapping()
    @Operation(summary = "Rota para listar todas as vagas de estacionamento ordenadas alfabeticamente pelo nome")
    public ResponseEntity<List<ParkingSpotDto>> listAll() {
        var result = this.listAllParkingSpotUseCase.execute();

        return ResponseEntity.ok(result);
    }

    @PostMapping("/park")
    @Operation(summary = "Rota para estacionar um veículo em uma vaga")
    public ResponseEntity<Void> parkVehicle(@RequestBody @Valid ParkVehicleInputDto body) {
        this.parkVehicleUseCase.execute(body);

        return ResponseEntity.ok().build();
    }
}

package com.example.parkscript.app.parking_spot;

import com.example.parkscript.app.parking_spot.usecases.calculate_amount.CalculateAmountUseCase;
import com.example.parkscript.app.parking_spot.usecases.calculate_amount.dtos.CalculateAmountOutputDto;
import com.example.parkscript.app.parking_spot.usecases.list_all.ListAllParkingSpotUseCase;
import com.example.parkscript.app.parking_spot.usecases.list_all.dtos.ListAllParkingSpotOutputDto;
import com.example.parkscript.app.parking_spot.usecases.park_vehicle.ParkVehicleUseCase;
import com.example.parkscript.app.parking_spot.usecases.park_vehicle.dtos.ParkVehicleInputDto;
import com.example.parkscript.app.parking_spot.usecases.release_vehicle.ReleaseVehicleUseCase;
import com.example.parkscript.app.parking_spot.usecases.release_vehicle.dtos.ReleaseVehicleInputDto;
import com.example.parkscript.shared.dtos.SuccessMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-spots")
@RequiredArgsConstructor
@Tag(name = "Vagas de estacionamento")
public class ParkingSpotController {
    private final ListAllParkingSpotUseCase listAllParkingSpotUseCase;
    private final ParkVehicleUseCase parkVehicleUseCase;
    private final ReleaseVehicleUseCase releaseVehicleUseCase;
    private final CalculateAmountUseCase calculateAmountUseCase;

    @GetMapping()
    @Operation(summary = "Rota para listar todas as vagas de estacionamento ordenadas alfabeticamente pelo nome")
    public ResponseEntity<ListAllParkingSpotOutputDto> listAll() {
        var result = this.listAllParkingSpotUseCase.execute();

        return ResponseEntity.ok(result);
    }

    @PostMapping("/park")
    @Operation(summary = "Rota para estacionar um veículo em uma vaga")
    public ResponseEntity<SuccessMessage> parkVehicle(@RequestBody @Valid ParkVehicleInputDto body) {
        this.parkVehicleUseCase.execute(body);

        return ResponseEntity.ok().body(new SuccessMessage("Veículo estacionado com sucesso!"));
    }

    @PostMapping("/release")
    @Operation(summary = "Rota para retirar um veículo de uma vaga")
    public ResponseEntity<SuccessMessage> releaseVehicle(@RequestBody @Valid ReleaseVehicleInputDto body) {
        this.releaseVehicleUseCase.execute(body);

        return ResponseEntity.ok().body(new SuccessMessage("Veículo removido com sucesso!"));
    }

    @GetMapping("{id}/amount")
    @Operation(summary = "Rota para obter o valor a ser pago por uma vaga de estacionamento")
    public ResponseEntity<CalculateAmountOutputDto> calculateAmount(@PathVariable String id) {
        var result = this.calculateAmountUseCase.execute(id);

        return ResponseEntity.ok(result);
    }
}

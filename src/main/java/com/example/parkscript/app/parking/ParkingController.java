package com.example.parkscript.app.parking;

import com.example.parkscript.app.parking.usecases.GetSumParkingAmountUseCase;
import com.example.parkscript.app.parking.usecases.dtos.GetSumParkingAmountOutputDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parkings")
@RequiredArgsConstructor
@Tag(name = "Registro de estacionamento")
public class ParkingController {
    private final GetSumParkingAmountUseCase getSumParkingAmountUseCase;

    @GetMapping()
    @Operation(summary = "Rota para obter o valor total arrecadado com estacionamento")
    public ResponseEntity<GetSumParkingAmountOutputDto> getSumParkingAmount() {
        var result = getSumParkingAmountUseCase.execute();

        return ResponseEntity.ok(result);
    }
}

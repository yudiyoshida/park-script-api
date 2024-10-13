package com.example.parkscript.app.vehicle;

import com.example.parkscript.app.vehicle.dtos.VehicleDto;
import com.example.parkscript.app.vehicle.usecases.create.CreateVehicleUseCase;
import com.example.parkscript.app.vehicle.usecases.create.dtos.CreateVehicleInputDto;
import com.example.parkscript.app.vehicle.usecases.create.dtos.CreateVehicleOutputDto;
import com.example.parkscript.app.vehicle.usecases.find_by_plate.FindVehicleByPlateUseCase;
import com.example.parkscript.app.vehicle.usecases.find_by_plate.dtos.FindVehicleByPlateInputDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
@Tag(name = "Veículos")
public class VehicleController {
    private final CreateVehicleUseCase createVehicleUseCase;
    private final FindVehicleByPlateUseCase findVehicleByPlateUseCase;

    @PostMapping()
    @Operation(summary = "Rota para cadastrar/atualizar um veículo")
    public ResponseEntity<CreateVehicleOutputDto> create(@RequestBody @Valid CreateVehicleInputDto body) {
        var result = this.createVehicleUseCase.execute(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/find-by-plate")
    @Operation(summary = "Rota para procurar veículo pela placa")
    public ResponseEntity<VehicleDto> findByPlate(@RequestBody @Valid FindVehicleByPlateInputDto body) {
        var result = this.findVehicleByPlateUseCase.execute(body.plate());

        return ResponseEntity.ok(result);
    }
}

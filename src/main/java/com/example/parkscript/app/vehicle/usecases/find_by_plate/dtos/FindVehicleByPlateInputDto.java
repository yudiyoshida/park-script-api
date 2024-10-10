package com.example.parkscript.app.vehicle.usecases.find_by_plate.dtos;

import jakarta.validation.constraints.NotBlank;

public record FindVehicleByPlateInputDto(
    @NotBlank(message = "Placa é um campo obrigatório")
    String plate
) { }

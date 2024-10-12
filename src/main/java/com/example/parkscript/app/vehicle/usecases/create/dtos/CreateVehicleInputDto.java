package com.example.parkscript.app.vehicle.usecases.create.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateVehicleInputDto(
    @NotBlank(message = "Placa é um campo obrigatório")
    String plate,

    @NotBlank(message = "Cor é um campo obrigatório")
    String color,

    @NotBlank(message = "Modelo é um campo obrigatório")
    String model
) { }

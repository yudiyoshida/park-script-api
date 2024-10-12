package com.example.parkscript.app.parking.usecases.park_vehicle.dtos;

import jakarta.validation.constraints.NotBlank;

public record ParkVehicleInputDto(
    @NotBlank(message = "Id da vaga de estacionamento é obrigatório")
    String parkingSpotId,

    @NotBlank(message = "Id do veículo é obrigatório")
    String vehicleId
) { }

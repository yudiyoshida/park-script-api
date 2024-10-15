package com.example.parkscript.app.parking_spot.usecases.park_vehicle;

import com.example.parkscript.app.client.repositories.ClientRepository;
import com.example.parkscript.app.parking_spot.repositories.ParkingSpotRepository;
import com.example.parkscript.app.parking_spot.usecases.park_vehicle.dtos.ParkVehicleInputDto;
import com.example.parkscript.app.vehicle.repositories.VehicleRepository;
import com.example.parkscript.shared.errors.AppException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ParkVehicleUseCase {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public void execute(ParkVehicleInputDto data) {
        var vehicle = this.vehicleRepository
                .findById(data.vehicleId())
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));

        var parkingSpot = this.parkingSpotRepository
                .findById(data.parkingSpotId())
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Vaga não encontrada"));

        if (parkingSpot.isOccupied()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Vaga de estacionamento já ocupada");
        }

        if (vehicle.isParked()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Veículo já está estacionado");
        }

        // incrementa 1 ponto no cartão fidelidade do cliente
        var client = vehicle.getClient();
        client.incrementLoyaltyCardPoints();
        this.clientRepository.save(client);

        // atualiza informações da vaga de estacionamento
        parkingSpot.parkVehicle(vehicle);
        this.parkingSpotRepository.save(parkingSpot);
    }
}

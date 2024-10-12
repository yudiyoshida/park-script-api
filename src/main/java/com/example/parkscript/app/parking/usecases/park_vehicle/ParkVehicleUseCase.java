package com.example.parkscript.app.parking.usecases.park_vehicle;

import com.example.parkscript.app.parking.domain.entities.Parking;
import com.example.parkscript.app.parking.repositories.ParkingRepository;
import com.example.parkscript.app.parking.usecases.park_vehicle.dtos.ParkVehicleInputDto;
import com.example.parkscript.app.parking.usecases.park_vehicle.dtos.ParkVehicleOutputDto;
import com.example.parkscript.app.parking_spot.repositories.ParkingSpotRepository;
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
    private ParkingRepository parkingRepository;

    @Transactional
    public ParkVehicleOutputDto execute(ParkVehicleInputDto data) {
        // ok: verificar se vaga existe
        // ok: verificar se veículo existe
        // ok: verificar se vaga está livre
        // ok: verificar se veículo está estacionado
        // ok: criar registro de estacionamento
        // ok: atualizar status da vaga
        // TODO: adicionar 1 ponto no plano fidelidade do cliente
        // TODO: retornar mensagem de sucesso

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

        // atualiza status da vaga de estacionamento
        parkingSpot.parkVehicle();
        this.parkingSpotRepository.save(parkingSpot);

        // incrementa 1 ponto no cartão fidelidade do cliente


        // cria registro de estacionamento
        var parking = new Parking(vehicle, parkingSpot);
        var newParking = this.parkingRepository.save(parking);

        return new ParkVehicleOutputDto(newParking.getId());
    }
}

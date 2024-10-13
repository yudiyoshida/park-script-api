package com.example.parkscript.app.vehicle.usecases.create;

import com.example.parkscript.app.client.repositories.ClientRepository;
import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import com.example.parkscript.app.vehicle.repositories.VehicleRepository;
import com.example.parkscript.app.vehicle.usecases.create.dtos.CreateVehicleInputDto;
import com.example.parkscript.app.vehicle.usecases.create.dtos.CreateVehicleOutputDto;
import com.example.parkscript.shared.errors.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CreateVehicleUseCase {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ClientRepository clientRepository;

    public CreateVehicleOutputDto execute(CreateVehicleInputDto data) {
        var vehicleExists = this.vehicleRepository.findByPlate(data.plate());
        var client = this.clientRepository
                .findById(data.clientId())
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        Vehicle vechile = vehicleExists.isPresent()
            ? new Vehicle(
                vehicleExists.get().getId(),
                vehicleExists.get().getPlate(),
                data.model(),
                data.color(),
                vehicleExists.get().getClient()
            )
            : new Vehicle(
                data.plate(),
                data.model(),
                data.color(),
                client
            );

        var newVehicle = this.vehicleRepository.save(vechile);

        return new CreateVehicleOutputDto(newVehicle.getId());
    }
}

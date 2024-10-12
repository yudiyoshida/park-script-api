package com.example.parkscript.app.vehicle.usecases.create;

import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import com.example.parkscript.app.vehicle.repositories.VehicleRepository;
import com.example.parkscript.app.vehicle.usecases.create.dtos.CreateVehicleInputDto;
import com.example.parkscript.app.vehicle.usecases.create.dtos.CreateVehicleOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateVehicleUseCase {
    @Autowired
    private VehicleRepository vehicleRepository;

    public CreateVehicleOutputDto execute(CreateVehicleInputDto data) {
        var vehicleExists = this.vehicleRepository.findByPlate(data.plate());

        Vehicle vechile = vehicleExists.isPresent()
            ? new Vehicle(
                vehicleExists.get().getId(),
                vehicleExists.get().getPlate(),
                data.model(),
                data.color()
            )
            : new Vehicle(
                data.plate(),
                data.model(),
                data.color()
            );

        var newVehicle = this.vehicleRepository.save(vechile);

        return new CreateVehicleOutputDto(newVehicle.getId());
    }
}

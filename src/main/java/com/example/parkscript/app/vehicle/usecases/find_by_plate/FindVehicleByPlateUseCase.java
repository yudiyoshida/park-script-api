package com.example.parkscript.app.vehicle.usecases.find_by_plate;

import com.example.parkscript.app.vehicle.dtos.VehicleDto;
import com.example.parkscript.app.vehicle.repositories.VehicleRepository;
import com.example.parkscript.shared.errors.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FindVehicleByPlateUseCase {
    @Autowired
    private VehicleRepository vehicleRepository;

    public VehicleDto execute(String plate) {
        return this.vehicleRepository.findByPlate(plate)
                .map(VehicleDto::new)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
    }
}

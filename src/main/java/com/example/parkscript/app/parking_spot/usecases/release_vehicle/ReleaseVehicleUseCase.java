package com.example.parkscript.app.parking_spot.usecases.release_vehicle;

import com.example.parkscript.app.parking_spot.repositories.ParkingSpotRepository;
import com.example.parkscript.app.parking_spot.usecases.release_vehicle.dtos.ReleaseVehicleInputDto;
import com.example.parkscript.shared.errors.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ReleaseVehicleUseCase {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public void execute(ReleaseVehicleInputDto data) {
        var parkingSpot = this.parkingSpotRepository
                .findById(data.parkingSpotId())
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Vaga não encontrada"));

        if (!parkingSpot.isOccupied()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "A vaga já se encontra vazia");
        }

        parkingSpot.releaseVehicle();
        this.parkingSpotRepository.save(parkingSpot);
    }
}

package com.example.parkscript.app.parking_spot.usecases.release_vehicle;

import com.example.parkscript.app.parking.domain.entities.Parking;
import com.example.parkscript.app.parking.repositories.ParkingRepository;
import com.example.parkscript.app.parking_spot.repositories.ParkingSpotRepository;
import com.example.parkscript.app.parking_spot.usecases.release_vehicle.dtos.ReleaseVehicleInputDto;
import com.example.parkscript.shared.errors.AppException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ReleaseVehicleUseCase {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private ParkingRepository parkingRepository;

    @Transactional
    public void execute(ReleaseVehicleInputDto data) {
        var parkingSpot = this.parkingSpotRepository
                .findById(data.parkingSpotId())
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Vaga não encontrada"));

        if (!parkingSpot.isOccupied()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "A vaga já se encontra vazia");
        }

        var parkingRegistry = new Parking(parkingSpot.getVehicle(), parkingSpot.getOccupiedAt());
        this.parkingRepository.save(parkingRegistry);

        parkingSpot.releaseVehicle();
        this.parkingSpotRepository.save(parkingSpot);
    }
}

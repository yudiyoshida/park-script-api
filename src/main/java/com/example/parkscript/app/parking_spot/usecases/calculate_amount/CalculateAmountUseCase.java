package com.example.parkscript.app.parking_spot.usecases.calculate_amount;

import com.example.parkscript.app.parking.domain.entities.Parking;
import com.example.parkscript.app.parking_spot.repositories.ParkingSpotRepository;
import com.example.parkscript.app.parking_spot.usecases.calculate_amount.dtos.CalculateAmountOutputDto;
import com.example.parkscript.shared.errors.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CalculateAmountUseCase {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public CalculateAmountOutputDto execute(String parkingSpotId) {
        var parkingSpot = this.parkingSpotRepository
                .findById(parkingSpotId)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Vaga não encontrada"));

        var amount = new Parking(parkingSpot.getVehicle(), parkingSpot.getOccupiedAt()).getAmount();

        return new CalculateAmountOutputDto(amount);
    }
}

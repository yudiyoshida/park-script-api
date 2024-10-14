package com.example.parkscript.app.parking.usecases;

import com.example.parkscript.app.parking.repositories.ParkingRepository;
import com.example.parkscript.app.parking.usecases.dtos.GetSumParkingAmountOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetSumParkingAmountUseCase {
    @Autowired
    private ParkingRepository parkingRepository;

    public GetSumParkingAmountOutputDto execute() {
        var total = this.parkingRepository.getTotalAmount();

        return new GetSumParkingAmountOutputDto(total);
    }
}

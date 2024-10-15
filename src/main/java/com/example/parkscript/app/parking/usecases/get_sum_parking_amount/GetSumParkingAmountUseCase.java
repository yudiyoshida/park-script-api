package com.example.parkscript.app.parking.usecases.get_sum_parking_amount;

import com.example.parkscript.app.parking.repositories.ParkingRepository;
import com.example.parkscript.app.parking.usecases.get_sum_parking_amount.dtos.GetSumParkingAmountOutputDto;
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

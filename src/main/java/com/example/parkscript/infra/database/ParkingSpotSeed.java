package com.example.parkscript.infra.database;

import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpot;
import com.example.parkscript.app.parking_spot.domain.entities.ParkingSpotType;
import com.example.parkscript.app.parking_spot.repositories.ParkingSpotRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpotSeed {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @PostConstruct
    public void seedDatabase() {
        if (parkingSpotRepository.count() == 0) {
            for (int i = 1; i <= 25; i++) {
                String name = i < 10 ? "A0" + i : "A" + i;
                ParkingSpotType type = i < 3 ? ParkingSpotType.HANDICAPPED : i < 6 ? ParkingSpotType.ELDERLY : ParkingSpotType.NORMAL;

                ParkingSpot parkingSpot = new ParkingSpot(name, type, false);
                parkingSpotRepository.save(parkingSpot);
            }

            System.out.println("Seed de banco de dados concluído com sucesso!");
        }
    }
}

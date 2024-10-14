package com.example.parkscript.app.parking.repositories;

import com.example.parkscript.app.parking.domain.entities.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> { }

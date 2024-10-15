package com.example.parkscript.app.client.domain.entities;

import com.example.parkscript.app.vehicle.domain.entities.Vehicle;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "clients")
@Getter
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private int loyaltyCardPoints;

    @OneToMany(mappedBy = "client")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Vehicle> vehicles;

    // creation
    public Client(String name, String cpf, String phone) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.loyaltyCardPoints = 0;
    }

    // restituition
    public Client(String id, String name, String cpf, String phone, int loyaltyCardPoints) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.loyaltyCardPoints = loyaltyCardPoints;
    }

    public void incrementLoyaltyCardPoints() {
        this.loyaltyCardPoints++;
    }
}

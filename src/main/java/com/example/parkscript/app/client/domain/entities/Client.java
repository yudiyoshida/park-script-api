package com.example.parkscript.app.client.domain.entities;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Client {
    private UUID id;
    private String name;
    private String cpf;
    private String phone;

    public Client(String name, String cpf, String phone) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
    }
}

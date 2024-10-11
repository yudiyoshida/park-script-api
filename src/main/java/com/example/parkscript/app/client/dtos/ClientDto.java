package com.example.parkscript.app.client.dtos;

import com.example.parkscript.app.client.domain.entities.Client;
import lombok.Getter;

@Getter
public class ClientDto {
    private final String id;
    private final String name;
    private final String cpf;
    private final String phone;

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.phone = client.getPhone();
    }
}

package com.example.parkscript.app.client.repositories;

import com.example.parkscript.app.client.domain.entities.Client;

import java.util.Optional;

public interface IClientRepository {
    Optional<Client> findByCpf(String cpf);
}

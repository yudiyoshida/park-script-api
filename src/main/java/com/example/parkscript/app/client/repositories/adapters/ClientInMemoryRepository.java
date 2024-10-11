package com.example.parkscript.app.client.repositories.adapters;

import com.example.parkscript.app.client.domain.entities.Client;
import com.example.parkscript.app.client.repositories.IClientRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientInMemoryRepository implements IClientRepository {
    private final List<Client> clients = new ArrayList<>();

    public ClientInMemoryRepository() {
        this.clients.add(new Client("John Doe", "123.456.789-01", "(11) 99999-9999"));
        this.clients.add(new Client("Jane Doe", "109.876.543-21", "(11) 88888-8888"));
    }

    @Override
    public Optional<Client> findByCpf(String cpf) {
        return this.clients.stream()
            .filter(client -> client.getCpf().equals(cpf))
            .findFirst();
    }
}

package com.example.parkscript.app.client.repositories;

import com.example.parkscript.app.client.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByCpf(String cpf);
}

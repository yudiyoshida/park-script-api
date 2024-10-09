package com.example.parkscript.app.client.usecases.find_by_cpf;

import com.example.parkscript.app.client.dtos.ClientDto;
import com.example.parkscript.app.client.repositories.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FindClientByCpfUseCase {
    @Autowired
    @Qualifier("clientInMemoryRepository")
    private IClientRepository clientRepository;

    public ClientDto execute(String cpf) {
        var client = this.clientRepository.findByCpf(cpf);

        if (client.isEmpty()) {
            throw new RuntimeException("Client not found");
        }
        return new ClientDto(client.get());
    }
}

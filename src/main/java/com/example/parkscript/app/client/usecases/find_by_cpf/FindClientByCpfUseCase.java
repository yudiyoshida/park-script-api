package com.example.parkscript.app.client.usecases.find_by_cpf;

import com.example.parkscript.app.client.dtos.ClientDto;
import com.example.parkscript.app.client.repositories.ClientRepository;
import com.example.parkscript.shared.errors.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FindClientByCpfUseCase {
    @Autowired
    private ClientRepository clientRepository;

    public ClientDto execute(String cpf) {
        var client = this.clientRepository.findByCpf(cpf);

        if (client.isEmpty()) {
            throw new AppException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
        return new ClientDto(client.get());
    }
}

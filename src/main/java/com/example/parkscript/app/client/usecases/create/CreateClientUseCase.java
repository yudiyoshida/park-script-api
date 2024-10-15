package com.example.parkscript.app.client.usecases.create;

import com.example.parkscript.app.client.domain.entities.Client;
import com.example.parkscript.app.client.repositories.ClientRepository;
import com.example.parkscript.app.client.usecases.create.dtos.CreateClientInputDto;
import com.example.parkscript.app.client.usecases.create.dtos.CreateClientOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateClientUseCase {
    @Autowired
    private ClientRepository clientRepository;

    public CreateClientOutputDto execute(CreateClientInputDto inputDto) {
        var clientAlreadyExists = this.clientRepository.findByCpf(inputDto.cpf());

        Client client = clientAlreadyExists.isPresent()
            ? new Client(
                clientAlreadyExists.get().getId(),
                inputDto.name(),
                clientAlreadyExists.get().getCpf(),
                inputDto.phone(),
                clientAlreadyExists.get().getLoyaltyCardPoints()
            )
            : new Client(
                inputDto.name(),
                inputDto.cpf(),
                inputDto.phone()
            );

        var newClient = this.clientRepository.save(client);

        return new CreateClientOutputDto(newClient.getId());
    }
}

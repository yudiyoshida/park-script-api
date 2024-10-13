package com.example.parkscript.app.client;

import com.example.parkscript.app.client.dtos.ClientDto;
import com.example.parkscript.app.client.usecases.create.CreateClientUseCase;
import com.example.parkscript.app.client.usecases.create.dtos.CreateClientInputDto;
import com.example.parkscript.app.client.usecases.create.dtos.CreateClientOutputDto;
import com.example.parkscript.app.client.usecases.find_by_cpf.FindClientByCpfUseCase;
import com.example.parkscript.app.client.usecases.find_by_cpf.dtos.FindClientByCpfInputDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(name = "Clientes")
public class ClientController {
    private final CreateClientUseCase createClientUseCase;
    private final FindClientByCpfUseCase findClientByCpfUseCase;

    @PostMapping()
    @Operation(summary = "Rota para cadastrar/atualizar um cliente")
    public ResponseEntity<CreateClientOutputDto> create(@RequestBody @Valid CreateClientInputDto body) {
        var result = this.createClientUseCase.execute(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/find-by-cpf")
    @Operation(summary = "Rota para procurar cliente pelo cpf")
    public ResponseEntity<ClientDto> findByCpf(@RequestBody @Valid FindClientByCpfInputDto body) {
        var result = this.findClientByCpfUseCase.execute(body.cpf());

        return ResponseEntity.ok(result);
    }
}

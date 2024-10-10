package com.example.parkscript.app.client;

import com.example.parkscript.app.client.dtos.ClientDto;
import com.example.parkscript.app.client.usecases.find_by_cpf.FindClientByCpfUseCase;
import com.example.parkscript.app.client.usecases.find_by_cpf.dtos.FindClientByCpfInputDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final FindClientByCpfUseCase findClientByCpfUseCase;

    @PostMapping()
    public ResponseEntity<ClientDto> findByCpf(@RequestBody @Valid FindClientByCpfInputDto body) {
        var result = this.findClientByCpfUseCase.execute(body.cpf());

        return ResponseEntity.ok(result);
    }
}

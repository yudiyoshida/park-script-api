package com.example.parkscript.app.client.usecases.find_by_cpf.dtos;

import jakarta.validation.constraints.NotBlank;

public record FindClientByCpfInputDto(
    @NotBlank(message = "CPF é um campo obrigatório")
    String cpf
) { }

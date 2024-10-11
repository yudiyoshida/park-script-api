package com.example.parkscript.app.client.usecases.create.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateClientInputDto(
    @NotBlank(message = "Nome é um campo obrigatório")
    String name,

    @NotBlank(message = "CPF é um campo obrigatório")
    String cpf,

    @NotBlank(message = "Telefone é um campo obrigatório")
    String phone
) {}

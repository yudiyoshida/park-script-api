package com.example.parkscript.app.client.usecases.find_by_cpf;

import com.example.parkscript.app.client.domain.entities.Client;
import com.example.parkscript.app.client.dtos.ClientDto;
import com.example.parkscript.app.client.repositories.IClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FindClientByCpfUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private IClientRepository clientRepository;

    @InjectMocks
    private FindClientByCpfUseCase findClientByCpfUseCase;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    void shouldThrowAnErrorWhenClientIsNotFound() {
        // Given
        Mockito.when(this.clientRepository.findByCpf("")).thenReturn(Optional.empty());

        // When
        var exception = assertThrows(
            RuntimeException.class,
            () -> this.findClientByCpfUseCase.execute("")
        );

        // Then
        assertEquals("Cliente não encontrado", exception.getMessage());
    }

    @Test
    void shouldReturnAClientDtoWhenClientIsFound() {
        // Given
        var cpf = "12345678900";
        var client = new Client("John Doe", cpf, "00123456789");
        Mockito.when(this.clientRepository.findByCpf(cpf)).thenReturn(Optional.of(client));

        // When
        var result = this.findClientByCpfUseCase.execute(cpf);

        // Then
        assertThat(result).isInstanceOf(ClientDto.class);
        assertEquals(client.getId(), result.getId());
        assertEquals(client.getName(), result.getName());
        assertEquals(client.getCpf(), result.getCpf());
        assertEquals(client.getPhone(), result.getPhone());
    }
}
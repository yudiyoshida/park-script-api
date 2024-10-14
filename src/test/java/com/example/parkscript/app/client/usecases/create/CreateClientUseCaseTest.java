package com.example.parkscript.app.client.usecases.create;

import com.example.parkscript.app.client.domain.entities.Client;
import com.example.parkscript.app.client.repositories.ClientRepository;
import com.example.parkscript.app.client.usecases.create.dtos.CreateClientInputDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreateClientUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private CreateClientUseCase createClientUseCase;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    void shouldCreateClientWhenIsNotFoundByCpf() {
        // Given
        String name = "Fulano";
        String cpf = "12345678909";
        String phone = "00123456789";

        Mockito.when(this.clientRepository.findByCpf(cpf)).thenReturn(Optional.empty());
        Mockito.when(this.clientRepository.save(ArgumentMatchers.any(Client.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        this.createClientUseCase.execute(new CreateClientInputDto(name, cpf, phone));

        // Then
        Mockito.verify(this.clientRepository).save(clientCaptor.capture());

        Client client = clientCaptor.getValue();
        assertNull(client.getId());
        assertEquals(name, client.getName());
        assertEquals(cpf, client.getCpf());
        assertEquals(phone, client.getPhone());
    }

    @Test
    void shouldUpdateClientWhenIsFoundByCpf() {
        // Given
        String clientId = "c-id";
        String name = "Fulano";
        String cpf = "12345678909";
        String phone = "00123456789";

        Client client = new Client(clientId, name, cpf, phone);
        Mockito.when(this.clientRepository.findByCpf(cpf)).thenReturn(Optional.of(client));
        Mockito.when(this.clientRepository.save(ArgumentMatchers.any(Client.class))).thenAnswer(invocation -> {
            return invocation.getArgument(0);
        });

        // When
        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        this.createClientUseCase.execute(new CreateClientInputDto(name, cpf, phone));

        // Then
        Mockito.verify(this.clientRepository).save(clientCaptor.capture());

        Client updatedClient = clientCaptor.getValue();
        assertEquals(clientId, updatedClient.getId());
        assertEquals(name, updatedClient.getName());
        assertEquals(cpf, updatedClient.getCpf());
        assertEquals(phone, updatedClient.getPhone());
    }
}
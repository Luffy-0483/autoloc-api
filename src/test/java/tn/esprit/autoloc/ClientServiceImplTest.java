package tn.esprit.autoloc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.ClientRepository;
import tn.esprit.autoloc.service.ClientServiceImpl;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setIdClient(1L);
        client.setNom("Ben Salah");
        client.setPrenom("Mohamed");
        client.setEmail("mohamed.bensalah@gmail.com");
        client.setTelephone("98123456");
        client.setNumPermis("B123456");
        client.setDateInscription(LocalDate.now());
    }

    @Test
    @DisplayName("retrieveAllClients - Devrait retourner la liste des clients")
    void testRetrieveAllClients() {
        when(clientRepository.findAll()).thenReturn(Arrays.asList(client));

        List<Client> clients = clientService.retrieveAllClients();

        assertThat(clients).hasSize(1);
        assertThat(clients.get(0).getNom()).isEqualTo("Ben Salah");
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("retrieveClient - Devrait retourner le client s'il existe")
    void testRetrieveClientFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client result = clientService.retrieveClient(1L);

        assertThat(result).isNotNull();
        assertThat(result.getIdClient()).isEqualTo(1L);
        assertThat(result.getEmail()).isEqualTo("mohamed.bensalah@gmail.com");
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("retrieveClient - Devrait lancer ResourceNotFoundException s'il n'existe pas")
    void testRetrieveClientNotFound() {
        when(clientRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> clientService.retrieveClient(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Client introuvable");

        verify(clientRepository, times(1)).findById(99L);
    }

    @Test
    @DisplayName("addClient - Devrait sauvegarder et retourner le client")
    void testAddClient() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client result = clientService.addClient(client);

        assertThat(result).isNotNull();
        assertThat(result.getNumPermis()).isEqualTo("B123456");
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    @DisplayName("removeClient - Devrait supprimer le client existant")
    void testRemoveClient() {
        when(clientRepository.existsById(1L)).thenReturn(true);
        doNothing().when(clientRepository).deleteById(1L);

        clientService.removeClient(1L);

        verify(clientRepository, times(1)).deleteById(1L);
    }
}

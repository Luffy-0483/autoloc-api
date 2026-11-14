package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.ClientRepository;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<Client> retrieveAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client retrieveClient(Long idClient) {
        return clientRepository.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable avec l'id : " + idClient));
    }

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void removeClient(Long idClient) {
        if (!clientRepository.existsById(idClient)) {
            throw new ResourceNotFoundException("Impossible de supprimer : Client introuvable avec l'id : " + idClient);
        }
        clientRepository.deleteById(idClient);
    }

    @Override
    public Client modifyClient(Client client) {
        return clientRepository.save(client);
    }
}

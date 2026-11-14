package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@Tag(name = "Gestion des Clients", description = "Endpoints de gestion des clients et permis de conduire")
@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientRestController {

    private final IClientService clientService;

    // http://localhost:8089/gestionprojets/client/retrieve-all-clients
    @Operation(summary = "Récupérer tous les clients", description = "Récupère la liste de tous les clients de la base")
    @GetMapping("/retrieve-all-clients")
    public List<Client> getClients() {
        return clientService.retrieveAllClients();
    }

    // http://localhost:8089/gestionprojets/client/retrieve-client/1
    @Operation(summary = "Récupérer un client par son id", description = "Retourne les détails d'un client spécifique")
    @GetMapping("/retrieve-client/{client-id}")
    public Client retrieveClient(@PathVariable("client-id") Long clientId) {
        return clientService.retrieveClient(clientId);
    }

    // http://localhost:8089/gestionprojets/client/add-client
    @Operation(summary = "Ajouter un client", description = "Enregistre un nouveau client dans la base de données")
    @PostMapping("/add-client")
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    // http://localhost:8089/gestionprojets/client/modify-client
    @Operation(summary = "Modifier un client", description = "Met à jour les données personnelles d'un client")
    @PutMapping("/modify-client")
    public Client modifyClient(@RequestBody Client client) {
        return clientService.modifyClient(client);
    }

    // http://localhost:8089/gestionprojets/client/remove-client/1
    @Operation(summary = "Supprimer un client", description = "Supprime le client correspondant à l'identifiant fourni")
    @DeleteMapping("/remove-client/{client-id}")
    public void removeClient(@PathVariable("client-id") Long clientId) {
        clientService.removeClient(clientId);
    }
}

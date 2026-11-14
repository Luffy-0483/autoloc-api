package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@Tag(name = "Gestion des Contrats", description = "Endpoints de gestion des contrats de location")
@RestController
@AllArgsConstructor
@RequestMapping("/contrat")
public class ContratRestController {

    private final IContratService contratService;

    // http://localhost:8089/gestionprojets/contrat/retrieve-all-contrats
    @Operation(summary = "Récupérer tous les contrats", description = "Retourne la liste complète de tous les contrats de location")
    @GetMapping("/retrieve-all-contrats")
    public List<Contrat> getContrats() {
        return contratService.retrieveAllContrats();
    }

    // http://localhost:8089/gestionprojets/contrat/retrieve-contrat/1
    @Operation(summary = "Récupérer un contrat par ID", description = "Retourne les détails d'un contrat donné")
    @GetMapping("/retrieve-contrat/{contrat-id}")
    public Contrat retrieveContrat(@PathVariable("contrat-id") Long contratId) {
        return contratService.retrieveContrat(contratId);
    }

    // http://localhost:8089/gestionprojets/contrat/add-contrat
    @Operation(summary = "Créer un contrat", description = "Enregistre un nouveau contrat de location")
    @PostMapping("/add-contrat")
    public Contrat addContrat(@RequestBody Contrat contrat) {
        return contratService.addContrat(contrat);
    }

    // http://localhost:8089/gestionprojets/contrat/modify-contrat
    @Operation(summary = "Modifier un contrat", description = "Met à jour un contrat existant")
    @PutMapping("/modify-contrat")
    public Contrat modifyContrat(@RequestBody Contrat contrat) {
        return contratService.modifyContrat(contrat);
    }

    // http://localhost:8089/gestionprojets/contrat/remove-contrat/1
    @Operation(summary = "Supprimer un contrat", description = "Supprime un contrat de location par son ID")
    @DeleteMapping("/remove-contrat/{contrat-id}")
    public void removeContrat(@PathVariable("contrat-id") Long contratId) {
        contratService.removeContrat(contratId);
    }

    // http://localhost:8089/gestionprojets/contrat/affecter-reservation/1/2
    @Operation(summary = "Affecter un contrat à une réservation", description = "Associe le contrat spécifié à la réservation correspondante")
    @PutMapping("/affecter-reservation/{contrat-id}/{reservation-id}")
    public void affecterContratAReservation(@PathVariable("contrat-id") Long contratId,
                                           @PathVariable("reservation-id") Long reservationId) {
        contratService.affecterContratAReservation(contratId, reservationId);
    }
}

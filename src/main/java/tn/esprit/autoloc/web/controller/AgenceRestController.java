package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@Tag(name = "Gestion des Agences", description = "Endpoints de gestion des agences de location")
@RestController
@AllArgsConstructor
@RequestMapping("/agence")
public class AgenceRestController {

    private final IAgenceService agenceService;

    // http://localhost:8089/gestionprojets/agence/retrieve-all-agences
    @Operation(summary = "Récupérer toutes les agences", description = "Récupère la liste complète des agences enregistrées")
    @GetMapping("/retrieve-all-agences")
    public List<Agence> getAgences() {
        return agenceService.retrieveAllAgences();
    }

    // http://localhost:8089/gestionprojets/agence/retrieve-agence/1
    @Operation(summary = "Récupérer une agence par son identifiant", description = "Retourne les détails d'une agence selon son id")
    @GetMapping("/retrieve-agence/{agence-id}")
    public Agence retrieveAgence(@PathVariable("agence-id") Long agenceId) {
        return agenceService.retrieveAgence(agenceId);
    }

    // http://localhost:8089/gestionprojets/agence/add-agence
    @Operation(summary = "Ajouter une agence", description = "Crée une nouvelle agence de location")
    @PostMapping("/add-agence")
    public Agence addAgence(@RequestBody Agence agence) {
        return agenceService.addAgence(agence);
    }

    // http://localhost:8089/gestionprojets/agence/modify-agence
    @Operation(summary = "Modifier une agence", description = "Met à jour les informations d'une agence existante")
    @PutMapping("/modify-agence")
    public Agence modifyAgence(@RequestBody Agence agence) {
        return agenceService.modifyAgence(agence);
    }

    // http://localhost:8089/gestionprojets/agence/remove-agence/1
    @Operation(summary = "Supprimer une agence", description = "Supprime une agence selon son identifiant")
    @DeleteMapping("/remove-agence/{agence-id}")
    public void removeAgence(@PathVariable("agence-id") Long agenceId) {
        agenceService.removeAgence(agenceId);
    }

    // http://localhost:8089/gestionprojets/agence/affecter-employe/1/2
    @Operation(summary = "Affecter un employé à une agence", description = "Associe un employé existant à une agence donnée")
    @PutMapping("/affecter-employe/{employe-id}/{agence-id}")
    public void affecterEmployeAAgence(@PathVariable("employe-id") Long employeId,
                                      @PathVariable("agence-id") Long agenceId) {
        agenceService.affecterEmployeAAgence(employeId, agenceId);
    }
}

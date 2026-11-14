package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@Tag(name = "Gestion des Véhicules", description = "Endpoints de gestion de la flotte de véhicules")
@RestController
@AllArgsConstructor
@RequestMapping("/vehicule")
public class VehiculeRestController {

    private final IVehiculeService vehiculeService;

    // http://localhost:8089/gestionprojets/vehicule/retrieve-all-vehicules
    @Operation(summary = "Récupérer tous les véhicules", description = "Retourne la liste complète des véhicules du parc automobile")
    @GetMapping("/retrieve-all-vehicules")
    public List<Vehicule> getVehicules() {
        return vehiculeService.retrieveAllVehicules();
    }

    // http://localhost:8089/gestionprojets/vehicule/retrieve-vehicule/1
    @Operation(summary = "Récupérer un véhicule par son ID", description = "Retourne les détails techniques et statut d'un véhicule")
    @GetMapping("/retrieve-vehicule/{vehicule-id}")
    public Vehicule retrieveVehicule(@PathVariable("vehicule-id") Long vehiculeId) {
        return vehiculeService.retrieveVehicule(vehiculeId);
    }

    // http://localhost:8089/gestionprojets/vehicule/add-vehicule
    @Operation(summary = "Ajouter un véhicule", description = "Enregistre un nouveau véhicule dans le parc automobile")
    @PostMapping("/add-vehicule")
    public Vehicule addVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.addVehicule(vehicule);
    }

    // http://localhost:8089/gestionprojets/vehicule/modify-vehicule
    @Operation(summary = "Modifier un véhicule", description = "Met à jour les informations d'un véhicule")
    @PutMapping("/modify-vehicule")
    public Vehicule modifyVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.modifyVehicule(vehicule);
    }

    // http://localhost:8089/gestionprojets/vehicule/remove-vehicule/1
    @Operation(summary = "Supprimer un véhicule", description = "Supprime un véhicule du parc automobile par son identifiant")
    @DeleteMapping("/remove-vehicule/{vehicule-id}")
    public void removeVehicule(@PathVariable("vehicule-id") Long vehiculeId) {
        vehiculeService.removeVehicule(vehiculeId);
    }

    // http://localhost:8089/gestionprojets/vehicule/affecter-agence/1/2
    @Operation(summary = "Affecter un véhicule à une agence", description = "Associe le véhicule à une agence locale spécifique")
    @PutMapping("/affecter-agence/{vehicule-id}/{agence-id}")
    public void affecterVehiculeAAgence(@PathVariable("vehicule-id") Long vehiculeId,
                                       @PathVariable("agence-id") Long agenceId) {
        vehiculeService.affecterVehiculeAAgence(vehiculeId, agenceId);
    }
}

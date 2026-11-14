package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@Tag(name = "Gestion des Équipements", description = "Endpoints de gestion des options et équipements des véhicules")
@RestController
@AllArgsConstructor
@RequestMapping("/equipement")
public class EquipementRestController {

    private final IEquipementService equipementService;

    // http://localhost:8089/gestionprojets/equipement/retrieve-all-equipements
    @Operation(summary = "Récupérer tous les équipements", description = "Retourne la liste complète des équipements disponibles")
    @GetMapping("/retrieve-all-equipements")
    public List<Equipement> getEquipements() {
        return equipementService.retrieveAllEquipements();
    }

    // http://localhost:8089/gestionprojets/equipement/retrieve-equipement/1
    @Operation(summary = "Récupérer un équipement par son ID", description = "Retourne les détails d'un équipement donné")
    @GetMapping("/retrieve-equipement/{equipement-id}")
    public Equipement retrieveEquipement(@PathVariable("equipement-id") Long equipementId) {
        return equipementService.retrieveEquipement(equipementId);
    }

    // http://localhost:8089/gestionprojets/equipement/add-equipement
    @Operation(summary = "Ajouter un équipement", description = "Enregistre un nouvel équipement (ex: GPS, Siège bébé)")
    @PostMapping("/add-equipement")
    public Equipement addEquipement(@RequestBody Equipement equipement) {
        return equipementService.addEquipement(equipement);
    }

    // http://localhost:8089/gestionprojets/equipement/modify-equipement
    @Operation(summary = "Modifier un équipement", description = "Met à jour le libellé d'un équipement")
    @PutMapping("/modify-equipement")
    public Equipement modifyEquipement(@RequestBody Equipement equipement) {
        return equipementService.modifyEquipement(equipement);
    }

    // http://localhost:8089/gestionprojets/equipement/remove-equipement/1
    @Operation(summary = "Supprimer un équipement", description = "Supprime un équipement selon son ID")
    @DeleteMapping("/remove-equipement/{equipement-id}")
    public void removeEquipement(@PathVariable("equipement-id") Long equipementId) {
        equipementService.removeEquipement(equipementId);
    }

    // http://localhost:8089/gestionprojets/equipement/affecter-vehicule/1/2
    @Operation(summary = "Affecter un équipement à un véhicule", description = "Ajoute l'équipement spécifié à la liste des équipements du véhicule")
    @PutMapping("/affecter-vehicule/{equipement-id}/{vehicule-id}")
    public void affecterEquipementAVehicule(@PathVariable("equipement-id") Long equipementId,
                                           @PathVariable("vehicule-id") Long vehiculeId) {
        equipementService.affecterEquipementAVehicule(equipementId, vehiculeId);
    }
}

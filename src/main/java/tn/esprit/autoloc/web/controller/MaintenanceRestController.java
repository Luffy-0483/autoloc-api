package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@Tag(name = "Gestion des Maintenances", description = "Endpoints de gestion des entretiens et réparations de véhicules")
@RestController
@AllArgsConstructor
@RequestMapping("/maintenance")
public class MaintenanceRestController {

    private final IMaintenanceService maintenanceService;

    // http://localhost:8089/gestionprojets/maintenance/retrieve-all-maintenances
    @Operation(summary = "Récupérer toutes les maintenances", description = "Retourne la liste de toutes les opérations de maintenance")
    @GetMapping("/retrieve-all-maintenances")
    public List<Maintenance> getMaintenances() {
        return maintenanceService.retrieveAllMaintenances();
    }

    // http://localhost:8089/gestionprojets/maintenance/retrieve-maintenance/1
    @Operation(summary = "Récupérer une maintenance par ID", description = "Retourne les détails d'une maintenance spécifique")
    @GetMapping("/retrieve-maintenance/{maintenance-id}")
    public Maintenance retrieveMaintenance(@PathVariable("maintenance-id") Long maintenanceId) {
        return maintenanceService.retrieveMaintenance(maintenanceId);
    }

    // http://localhost:8089/gestionprojets/maintenance/add-maintenance
    @Operation(summary = "Enregistrer une maintenance", description = "Crée un nouvel enregistrement de maintenance")
    @PostMapping("/add-maintenance")
    public Maintenance addMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.addMaintenance(maintenance);
    }

    // http://localhost:8089/gestionprojets/maintenance/modify-maintenance
    @Operation(summary = "Modifier une maintenance", description = "Met à jour une opération de maintenance existante")
    @PutMapping("/modify-maintenance")
    public Maintenance modifyMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.modifyMaintenance(maintenance);
    }

    // http://localhost:8089/gestionprojets/maintenance/remove-maintenance/1
    @Operation(summary = "Supprimer une maintenance", description = "Supprime un enregistrement de maintenance par son ID")
    @DeleteMapping("/remove-maintenance/{maintenance-id}")
    public void removeMaintenance(@PathVariable("maintenance-id") Long maintenanceId) {
        maintenanceService.removeMaintenance(maintenanceId);
    }

    // http://localhost:8089/gestionprojets/maintenance/affecter-vehicule/1/2
    @Operation(summary = "Affecter une maintenance à un véhicule", description = "Associe l'opération de maintenance au véhicule concerné")
    @PutMapping("/affecter-vehicule/{maintenance-id}/{vehicule-id}")
    public void affecterMaintenanceAVehicule(@PathVariable("maintenance-id") Long maintenanceId,
                                            @PathVariable("vehicule-id") Long vehiculeId) {
        maintenanceService.affecterMaintenanceAVehicule(maintenanceId, vehiculeId);
    }
}

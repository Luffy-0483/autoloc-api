package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.isp.IVehiculeConsultationService;

import java.util.List;

/**
 * SOLID ISP (Interface Segregation Principle).
 * Contrôleur de consultation publique des véhicules.
 * Il dépend EXCLUSIVEMENT de l'interface ségréguée IVehiculeConsultationService
 * plutôt que de l'interface globale IVehiculeService. Il n'a aucun accès aux méthodes
 * d'écriture, de suppression ou d'affectation.
 */
@Tag(name = "Catalogue Véhicules (ISP)", description = "Endpoints de consultation ségrégués selon le principe ISP")
@RestController
@AllArgsConstructor
@RequestMapping("/catalogue-vehicules")
public class VehiculeConsultationRestController {

    private final IVehiculeConsultationService consultationService;

    @Operation(summary = "Consulter tout le catalogue de véhicules", description = "Retourne la liste des véhicules via l'interface ségréguée IVehiculeConsultationService")
    @GetMapping("/consulter-tous")
    public List<Vehicule> consulterTous() {
        return consultationService.retrieveAllVehicules();
    }

    @Operation(summary = "Consulter une fiche véhicule par ID", description = "Retourne la fiche détaillée d'un véhicule via l'interface ségréguée")
    @GetMapping("/consulter/{id}")
    public Vehicule consulterParId(@PathVariable("id") Long id) {
        return consultationService.retrieveVehicule(id);
    }
}

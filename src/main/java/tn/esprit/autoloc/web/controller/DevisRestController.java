package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.service.IDevisLocationService;
import tn.esprit.autoloc.web.dto.DevisLocationDTO;

/**
 * Contrôleur REST pour le calcul dynamique de devis de location.
 * Illustre en conditions réelles les patterns Decorator et Strategy.
 */
@Tag(name = "Calcul de Devis (Decorator & Strategy)", description = "Endpoints de calcul tarifaire dynamique combinant Decorator (options/TVA) et Strategy (remises)")
@RestController
@AllArgsConstructor
@RequestMapping("/devis")
public class DevisRestController {

    private final IDevisLocationService devisLocationService;

    // http://localhost:8089/gestionprojets/devis/calculer?vehiculeId=1&nombreJours=5&assurance=true&gps=true&conducteurSupp=false&codeRemise=VIP
    @Operation(summary = "Calculer un devis de location dynamique",
            description = "Applique le Decorator Pattern pour ajouter assurance, GPS, conducteur supplémentaire et TVA, puis applique le Strategy Pattern pour la remise client (VIP, ETUDIANT, FIDELITE)")
    @GetMapping("/calculer")
    public DevisLocationDTO calculerDevis(
            @Parameter(description = "Identifiant du véhicule", example = "1")
            @RequestParam("vehiculeId") Long vehiculeId,
            @Parameter(description = "Durée de la location en jours", example = "5")
            @RequestParam(name = "nombreJours", defaultValue = "1") int nombreJours,
            @Parameter(description = "Option Assurance Tous Risques", example = "true")
            @RequestParam(name = "assurance", defaultValue = "false") boolean assurance,
            @Parameter(description = "Option Système GPS", example = "false")
            @RequestParam(name = "gps", defaultValue = "false") boolean gps,
            @Parameter(description = "Option Conducteur Supplémentaire", example = "false")
            @RequestParam(name = "conducteurSupp", defaultValue = "false") boolean conducteurSupp,
            @Parameter(description = "Code de profil de remise (VIP, ETUDIANT, FIDELITE, STANDARD)", example = "VIP")
            @RequestParam(name = "codeRemise", defaultValue = "STANDARD") String codeRemise) {

        return devisLocationService.calculerDevis(vehiculeId, nombreJours, assurance, gps, conducteurSupp, codeRemise);
    }
}

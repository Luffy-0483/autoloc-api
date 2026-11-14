package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@Tag(name = "Gestion des Paiements", description = "Endpoints de gestion des transactions et règlements des contrats")
@RestController
@AllArgsConstructor
@RequestMapping("/paiement")
public class PaiementRestController {

    private final IPaiementService paiementService;

    // http://localhost:8089/gestionprojets/paiement/retrieve-all-paiements
    @Operation(summary = "Récupérer tous les paiements", description = "Retourne la liste complète des paiements enregistrés")
    @GetMapping("/retrieve-all-paiements")
    public List<Paiement> getPaiements() {
        return paiementService.retrieveAllPaiements();
    }

    // http://localhost:8089/gestionprojets/paiement/retrieve-paiement/1
    @Operation(summary = "Récupérer un paiement par ID", description = "Retourne les détails d'un paiement spécifique")
    @GetMapping("/retrieve-paiement/{paiement-id}")
    public Paiement retrievePaiement(@PathVariable("paiement-id") Long paiementId) {
        return paiementService.retrievePaiement(paiementId);
    }

    // http://localhost:8089/gestionprojets/paiement/add-paiement
    @Operation(summary = "Ajouter un paiement", description = "Enregistre un nouveau paiement")
    @PostMapping("/add-paiement")
    public Paiement addPaiement(@RequestBody Paiement paiement) {
        return paiementService.addPaiement(paiement);
    }

    // http://localhost:8089/gestionprojets/paiement/modify-paiement
    @Operation(summary = "Modifier un paiement", description = "Met à jour un paiement existant")
    @PutMapping("/modify-paiement")
    public Paiement modifyPaiement(@RequestBody Paiement paiement) {
        return paiementService.modifyPaiement(paiement);
    }

    // http://localhost:8089/gestionprojets/paiement/remove-paiement/1
    @Operation(summary = "Supprimer un paiement", description = "Supprime un paiement selon son ID")
    @DeleteMapping("/remove-paiement/{paiement-id}")
    public void removePaiement(@PathVariable("paiement-id") Long paiementId) {
        paiementService.removePaiement(paiementId);
    }

    // http://localhost:8089/gestionprojets/paiement/add-paiement-affecter/1
    @Operation(summary = "Ajouter un paiement et l'affecter à un contrat", description = "Enregistre le paiement et le relie directement au contrat désigné")
    @PostMapping("/add-paiement-affecter/{contrat-id}")
    public Paiement ajouterPaiementEtAffecterAContrat(@RequestBody Paiement paiement,
                                                     @PathVariable("contrat-id") Long contratId) {
        return paiementService.ajouterPaiementEtAffecterAContrat(paiement, contratId);
    }

    // http://localhost:8089/gestionprojets/paiement/traiter-paiement/1
    @Operation(summary = "Traiter un paiement via Factory Pattern", description = "Délègue l'exécution de la transaction au processeur spécifique (Carte, Espèces, Virement, PayPal) déterminé par la Factory")
    @PostMapping("/traiter-paiement/{paiement-id}")
    public String traiterPaiement(@PathVariable("paiement-id") Long paiementId) {
        return paiementService.traiterPaiementViaFactory(paiementId);
    }
}

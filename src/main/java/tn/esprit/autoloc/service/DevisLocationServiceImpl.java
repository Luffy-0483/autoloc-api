package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.pattern.decorator.*;
import tn.esprit.autoloc.pattern.strategy.CalculRemiseService;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.web.dto.DevisLocationDTO;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.math.BigDecimal;

/**
 * Implémentation du service de devis illustrant :
 * - Le Decorator Pattern (assemblage dynamique base + options + TVA)
 * - Le Strategy Pattern & OCP (calcul de remise polymorphique)
 * - DIP (injection des dépendances d'abstractions)
 */
@Slf4j
@Service
@AllArgsConstructor
public class DevisLocationServiceImpl implements IDevisLocationService {

    private final VehiculeRepository vehiculeRepository;
    private final CalculRemiseService calculRemiseService;

    @Override
    public DevisLocationDTO calculerDevis(Long idVehicule,
                                          int nombreJours,
                                          boolean avecAssuranceTousRisques,
                                          boolean avecGps,
                                          boolean avecConducteurSupp,
                                          String codeRemise) {

        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException("Véhicule introuvable avec l'id : " + idVehicule));

        // 1. Composant de base (GoF Decorator)
        TarifLocation tarif = new BaseTarifLocation(vehicule.getTarifJournalier(), nombreJours);
        BigDecimal montantBase = tarif.getPrix();

        // 2. Décorateurs optionnels assemblés dynamiquement
        if (avecAssuranceTousRisques) {
            tarif = new AssuranceTousRisquesDecorator(tarif, nombreJours);
        }
        if (avecGps) {
            tarif = new GpsOptionDecorator(tarif);
        }
        if (avecConducteurSupp) {
            tarif = new ConducteurSupplementaireDecorator(tarif, nombreJours);
        }

        // 3. Application de la TVA (Décorateur fiscal en couche finale)
        tarif = new TvaDecorator(tarif);

        BigDecimal montantTotalTTC = tarif.getPrix();
        BigDecimal montantOptionsEtTva = montantTotalTTC.subtract(montantBase);

        // 4. Application de la stratégie de remise (GoF Strategy & SOLID OCP)
        String code = (codeRemise != null && !codeRemise.isBlank()) ? codeRemise : "STANDARD";
        BigDecimal montantFinalApresRemise = calculRemiseService.appliquerRemise(code, montantTotalTTC);

        log.info("[DEVIS] Devis généré pour véhicule {}: Total TTC={} TND, après remise {}={}",
                vehicule.getImmatriculation(), montantTotalTTC, code, montantFinalApresRemise);

        return DevisLocationDTO.builder()
                .idVehicule(vehicule.getIdVehicule())
                .immatriculation(vehicule.getImmatriculation())
                .nombreJours(nombreJours)
                .tarifJournalier(vehicule.getTarifJournalier())
                .montantBase(montantBase)
                .montantOptionsEtTva(montantOptionsEtTva)
                .montantTotalTTC(montantTotalTTC)
                .codeRemiseApplique(code)
                .montantApresRemise(montantFinalApresRemise)
                .detailCalcul(tarif.getDescription())
                .build();
    }
}

package tn.esprit.autoloc.pattern.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.ModePaiement;
import tn.esprit.autoloc.domain.Paiement;

/**
 * Processeur de paiement en Espèces.
 */
@Slf4j
@Component
public class EspecesPaiementProcessor implements PaiementProcessor {

    @Override
    public ModePaiement getModePaiement() {
        return ModePaiement.ESPECES;
    }

    @Override
    public void traiterPaiement(Paiement paiement) {
        log.info("[PAIEMENT ESPECES] Encaissement en caisse physique de {} TND pour le paiement id={}",
                paiement.getMontant(), paiement.getIdPaiement());
    }

    @Override
    public String getDetailsTransaction(Paiement paiement) {
        return "Paiement en espèces reçu en agence - Reçu émis pour: " + paiement.getMontant() + " TND";
    }
}

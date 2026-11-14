package tn.esprit.autoloc.pattern.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.ModePaiement;
import tn.esprit.autoloc.domain.Paiement;

/**
 * Processeur de paiement par Virement Bancaire.
 */
@Slf4j
@Component
public class VirementPaiementProcessor implements PaiementProcessor {

    @Override
    public ModePaiement getModePaiement() {
        return ModePaiement.VIREMENT;
    }

    @Override
    public void traiterPaiement(Paiement paiement) {
        log.info("[PAIEMENT VIREMENT] Rapprochement bancaire pour la référence virement de {} TND (paiement id={})",
                paiement.getMontant(), paiement.getIdPaiement());
    }

    @Override
    public String getDetailsTransaction(Paiement paiement) {
        return "Virement bancaire rapproché et crédité - Montant: " + paiement.getMontant() + " TND";
    }
}

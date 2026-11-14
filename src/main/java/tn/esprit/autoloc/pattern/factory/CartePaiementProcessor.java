package tn.esprit.autoloc.pattern.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.ModePaiement;
import tn.esprit.autoloc.domain.Paiement;

/**
 * Processeur de paiement par Carte Bancaire.
 */
@Slf4j
@Component
public class CartePaiementProcessor implements PaiementProcessor {

    @Override
    public ModePaiement getModePaiement() {
        return ModePaiement.CARTE;
    }

    @Override
    public void traiterPaiement(Paiement paiement) {
        log.info("[PAIEMENT CARTE] Autorisation bancaire et débit de {} TND pour le paiement id={}",
                paiement.getMontant(), paiement.getIdPaiement());
    }

    @Override
    public String getDetailsTransaction(Paiement paiement) {
        return "Transaction Carte Bancaire validée - Montant: " + paiement.getMontant() + " TND";
    }
}

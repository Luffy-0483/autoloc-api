package tn.esprit.autoloc.pattern.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.ModePaiement;
import tn.esprit.autoloc.domain.Paiement;

/**
 * Processeur de paiement via PayPal.
 */
@Slf4j
@Component
public class PayPalPaiementProcessor implements PaiementProcessor {

    @Override
    public ModePaiement getModePaiement() {
        return ModePaiement.PAYPAL;
    }

    @Override
    public void traiterPaiement(Paiement paiement) {
        log.info("[PAIEMENT PAYPAL] Exécution de l'ordre de capture PayPal de {} TND pour le paiement id={}",
                paiement.getMontant(), paiement.getIdPaiement());
    }

    @Override
    public String getDetailsTransaction(Paiement paiement) {
        return "Transaction PayPal complétée avec succès - Montant: " + paiement.getMontant() + " TND";
    }
}

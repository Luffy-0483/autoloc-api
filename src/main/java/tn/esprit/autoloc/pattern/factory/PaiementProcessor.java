package tn.esprit.autoloc.pattern.factory;

import tn.esprit.autoloc.domain.ModePaiement;
import tn.esprit.autoloc.domain.Paiement;

/**
 * GoF Factory Pattern - Interface Produit (Product).
 * Définit le contrat commun pour tous les processeurs de paiement.
 */
public interface PaiementProcessor {

    ModePaiement getModePaiement();

    void traiterPaiement(Paiement paiement);

    String getDetailsTransaction(Paiement paiement);
}

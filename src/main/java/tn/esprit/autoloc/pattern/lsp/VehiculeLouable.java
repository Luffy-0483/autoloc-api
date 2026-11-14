package tn.esprit.autoloc.pattern.lsp;

import java.math.BigDecimal;

/**
 * SOLID LSP (Liskov Substitution Principle).
 * Contrat de base garantissant le comportement commun à tout véhicule louable dans AutoLoc.
 * Toute sous-classe ou implémentation peut se substituer à cette interface sans violer d'invariant.
 */
public interface VehiculeLouable {

    Long getId();

    String getImmatriculation();

    String getModele();

    BigDecimal getTarifJournalier();

    boolean estPretPourLocation();
}

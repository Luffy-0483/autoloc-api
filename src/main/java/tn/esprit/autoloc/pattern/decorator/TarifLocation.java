package tn.esprit.autoloc.pattern.decorator;

import java.math.BigDecimal;

/**
 * GoF Decorator Pattern - Interface Composant (Component).
 * Définit le contrat pour le calcul tarifaire d'une location de véhicule.
 */
public interface TarifLocation {

    /**
     * Calcule le prix total de la location en cumulant la base et les options décorées.
     */
    BigDecimal getPrix();

    /**
     * Fournit le descriptif détaillé et la décomposition du tarif.
     */
    String getDescription();
}

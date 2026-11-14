package tn.esprit.autoloc.pattern.strategy;

/**
 * Atelier GoF & SOLID - Partie C (Étape 1).
 * Abstraction du patron Strategy définissant le contrat de calcul tarifaire d'une location.
 */
public interface StrategieTarification {

    /**
     * Calcule le montant total d'une location selon la politique tarifaire.
     *
     * @param tarifJournalier tarif de base par jour
     * @param jours nombre de jours de location
     * @return prix total calculé
     */
    double calculer(double tarifJournalier, int jours);
}

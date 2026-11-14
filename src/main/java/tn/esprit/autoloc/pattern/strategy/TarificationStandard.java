package tn.esprit.autoloc.pattern.strategy;

import org.springframework.stereotype.Component;

/**
 * Atelier GoF & SOLID - Partie C (Étape 2).
 * Politique Standard : tarif journalier x nombre de jours.
 */
@Component("tarificationStandard")
public class TarificationStandard implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        return tarifJournalier * jours;
    }
}

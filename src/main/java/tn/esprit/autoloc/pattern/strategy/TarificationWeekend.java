package tn.esprit.autoloc.pattern.strategy;

import org.springframework.stereotype.Component;

/**
 * Atelier GoF & SOLID - Partie C (Étape 2).
 * Politique Week-end : réduction de 10 %.
 */
@Component("tarificationWeekend")
public class TarificationWeekend implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        return (tarifJournalier * jours) * 0.90;
    }
}

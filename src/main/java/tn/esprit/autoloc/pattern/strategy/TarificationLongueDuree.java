package tn.esprit.autoloc.pattern.strategy;

import org.springframework.stereotype.Component;

/**
 * Atelier GoF & SOLID - Partie C (Étape 2).
 * Politique Longue durée : réduction de 20 %.
 */
@Component("tarificationLongueDuree")
public class TarificationLongueDuree implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        return (tarifJournalier * jours) * 0.80;
    }
}

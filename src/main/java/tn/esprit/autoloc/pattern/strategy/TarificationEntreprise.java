package tn.esprit.autoloc.pattern.strategy;

import org.springframework.stereotype.Component;

/**
 * Atelier GoF & SOLID - Partie C (Étape 4 : Faire évoluer l'application).
 * Nouvelle politique Entreprise : réduction de 15 %.
 * Ajoutée sans modifier aucune ligne de TarificationService (Respect strict de l'OCP).
 */
@Component("tarificationEntreprise")
public class TarificationEntreprise implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        return (tarifJournalier * jours) * 0.85;
    }
}

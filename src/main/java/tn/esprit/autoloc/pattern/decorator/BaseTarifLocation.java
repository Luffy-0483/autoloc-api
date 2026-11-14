package tn.esprit.autoloc.pattern.decorator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Composant Concret (Concrete Component).
 * Représente le tarif de base d'une location calculé sur le prix journalier et le nombre de jours.
 */
public class BaseTarifLocation implements TarifLocation {

    private final BigDecimal tarifJournalier;
    private final int nombreJours;

    public BaseTarifLocation(BigDecimal tarifJournalier, int nombreJours) {
        if (tarifJournalier == null || tarifJournalier.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le tarif journalier doit être positif.");
        }
        if (nombreJours <= 0) {
            throw new IllegalArgumentException("Le nombre de jours doit être supérieur à zéro.");
        }
        this.tarifJournalier = tarifJournalier;
        this.nombreJours = nombreJours;
    }

    @Override
    public BigDecimal getPrix() {
        return tarifJournalier.multiply(BigDecimal.valueOf(nombreJours)).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getDescription() {
        return "Tarif de base (" + nombreJours + " jours @ " + tarifJournalier + " TND/j = " + getPrix() + " TND)";
    }

    public int getNombreJours() {
        return nombreJours;
    }
}

package tn.esprit.autoloc.service.validation;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Vehicule;

import java.math.BigDecimal;

/**
 * Atelier GoF & SOLID - Partie B.1 : Principe de responsabilité unique (SRP).
 * Responsabilité unique : Valider les règles métier relatives aux véhicules.
 */
@Component
public class VehiculeValidator {

    /**
     * Vérifie que le tarif journalier du véhicule est strictement positif.
     *
     * @param vehicule le véhicule à valider
     * @throws IllegalArgumentException si le tarif est nul ou négatif
     */
    public void validerTarif(Vehicule vehicule) {
        if (vehicule == null) {
            throw new IllegalArgumentException("Le véhicule ne peut pas être null");
        }
        if (vehicule.getTarifJournalier() == null || vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le tarif doit être positif");
        }
    }

    /**
     * Vérifie le nombre de jours pour un calcul de tarif.
     *
     * @param nombreJours nombre de jours de location
     * @throws IllegalArgumentException si nombreJours <= 0
     */
    public void validerNombreJours(int nombreJours) {
        if (nombreJours <= 0) {
            throw new IllegalArgumentException("Le nombre de jours doit être positif");
        }
    }

    /**
     * Validation globale du véhicule.
     */
    public void validerVehicule(Vehicule vehicule) {
        validerTarif(vehicule);
        if (vehicule.getImmatriculation() == null || vehicule.getImmatriculation().isBlank()) {
            throw new IllegalArgumentException("L'immatriculation est obligatoire");
        }
    }
}

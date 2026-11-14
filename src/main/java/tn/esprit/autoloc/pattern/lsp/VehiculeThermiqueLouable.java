package tn.esprit.autoloc.pattern.lsp;

/**
 * Interface spécialisée pour les véhicules thermiques (essence / diesel).
 * Étend le contrat VehiculeLouable sans forcer les autres types (ex: électriques)
 * à implémenter la gestion du réservoir d'essence.
 */
public interface VehiculeThermiqueLouable extends VehiculeLouable {

    int getNiveauCarburant();

    void ravitaillerCarburant(int litres);
}

package tn.esprit.autoloc.pattern.lsp;

/**
 * Interface spécialisée pour les véhicules électriques.
 * Définit la gestion de la charge sans forcer de méthode carburant non applicable.
 */
public interface VehiculeElectriqueLouable extends VehiculeLouable {

    int getNiveauBatterie();

    void rechargerBatterie(int pourcent);
}

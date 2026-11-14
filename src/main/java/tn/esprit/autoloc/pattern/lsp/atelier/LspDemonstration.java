package tn.esprit.autoloc.pattern.lsp.atelier;

/**
 * Atelier GoF & SOLID - Partie D (Situation 3 : LSP).
 * Démonstration académique de la violation du principe de substitution de Liskov et de sa résolution.
 */
public class LspDemonstration {

    // ==========================================
    // 1. VIOLATION DE LSP (Anti-Pattern)
    // ==========================================

    public static class VehiculeMotoriseBase {
        public void demarrerMoteur() {
            // Démarrage normal du moteur thermique
        }
        public void fairePleinCarburant(int litres) {
            // Remplissage réservoir
        }
    }

    /**
     * Viole LSP : Cette sous-classe hérite de méthodes qu'elle ne peut pas honorer
     * et lève systématiquement UnsupportedOperationException.
     * Tout code client manipulant VehiculeMotoriseBase plantera si une BicycletteLocation lui est passée !
     */
    public static class BicycletteLocationViolation extends VehiculeMotoriseBase {
        @Override
        public void demarrerMoteur() {
            throw new UnsupportedOperationException("Une bicyclette n'a pas de moteur !");
        }

        @Override
        public void fairePleinCarburant(int litres) {
            throw new UnsupportedOperationException("Une bicyclette ne consomme pas de carburant !");
        }
    }

    // ==========================================
    // 2. SOLUTION CONFORME À LSP
    // ==========================================

    /**
     * Contrat de base partagé par tous les engins louables.
     */
    public interface EnginLouable {
        String getIdentifiant();
        boolean estDisponible();
    }

    /**
     * Sous-contrat spécialisé pour les véhicules équipés d'une motorisation.
     */
    public interface EnginMotorise extends EnginLouable {
        void demarrerMoteur();
        void ravitaillerEnergie(int quantite);
    }

    public static class AutomobileLouable implements EnginMotorise {
        private final String immatriculation;
        public AutomobileLouable(String immatriculation) { this.immatriculation = immatriculation; }
        @Override public String getIdentifiant() { return immatriculation; }
        @Override public boolean estDisponible() { return true; }
        @Override public void demarrerMoteur() { /* Démarre */ }
        @Override public void ravitaillerEnergie(int quantite) { /* Ravitaille */ }
    }

    public static class BicycletteLouable implements EnginLouable {
        private final String codeVelo;
        public BicycletteLouable(String codeVelo) { this.codeVelo = codeVelo; }
        @Override public String getIdentifiant() { return codeVelo; }
        @Override public boolean estDisponible() { return true; }
        // Ne déclare ni ne force de méthode demarrerMoteur() !
    }
}

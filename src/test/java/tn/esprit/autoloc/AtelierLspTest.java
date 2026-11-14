package tn.esprit.autoloc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tn.esprit.autoloc.pattern.lsp.atelier.LspDemonstration.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Atelier GoF & SOLID - Tests Partie D Situation 3 (LSP)")
class AtelierLspTest {

    @Test
    @DisplayName("Démonstration de la VIOLATION de LSP : la sous-classe lève UnsupportedOperationException")
    void testViolationLSP() {
        VehiculeMotoriseBase vehicule = new BicycletteLocationViolation();

        // Le client qui croit manipuler un véhicule motorisé générique reçoit une exception d'exécution !
        assertThrows(UnsupportedOperationException.class, vehicule::demarrerMoteur);
        assertThrows(UnsupportedOperationException.class, () -> vehicule.fairePleinCarburant(10));
    }

    @Test
    @DisplayName("Démonstration de la SOLUTION conforme à LSP : hiérarchie de contrats spécialisés")
    void testSolutionLSP() {
        EnginLouable auto = new AutomobileLouable("250-TUN-888");
        EnginLouable velo = new BicycletteLouable("VELO-042");

        // Tout EnginLouable peut être manipulé sans exception
        assertTrue(auto.estDisponible());
        assertTrue(velo.estDisponible());

        // Seul l'engin motorisé expose demarrerMoteur
        if (auto instanceof EnginMotorise motorise) {
            assertDoesNotThrow(motorise::demarrerMoteur);
        }
    }
}

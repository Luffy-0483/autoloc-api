package tn.esprit.autoloc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tn.esprit.autoloc.pattern.lsp.VehiculeLouable;
import tn.esprit.autoloc.pattern.lsp.VoitureElectrique;
import tn.esprit.autoloc.pattern.lsp.VoitureThermique;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests du Principe SOLID LSP - Liskov Substitution Principle")
class LspSubstitutionTest {

    @Test
    @DisplayName("Tout VehiculeLouable doit pouvoir être manipulé polymorphiquement sans altérer le contrat")
    void testSubstitutionPolymorphique() {
        VehiculeLouable thermique = new VoitureThermique(1L, "250-TUN-1", "Clio 5", new BigDecimal("70.00"), 40);
        VehiculeLouable electrique = new VoitureElectrique(2L, "250-TUN-2", "Tesla Model 3", new BigDecimal("180.00"), 85);

        List<VehiculeLouable> flotte = List.of(thermique, electrique);

        for (VehiculeLouable vehicule : flotte) {
            assertNotNull(vehicule.getImmatriculation());
            assertNotNull(vehicule.getModele());
            assertTrue(vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) > 0);
            // Aucune UnsupportedOperationException n'est levée !
            assertDoesNotThrow(vehicule::estPretPourLocation);
        }

        assertTrue(thermique.estPretPourLocation());
        assertTrue(electrique.estPretPourLocation());
    }

    @Test
    @DisplayName("Les interfaces spécialisées gèrent leurs spécificités sans violer le contrat parent")
    void testOperationsSpecifiques() {
        VoitureThermique thermique = new VoitureThermique(1L, "250-TUN-1", "Clio 5", new BigDecimal("70.00"), 10);
        assertFalse(thermique.estPretPourLocation()); // 10% < 20%
        thermique.ravitaillerCarburant(50);
        assertEquals(60, thermique.getNiveauCarburant());
        assertTrue(thermique.estPretPourLocation());

        VoitureElectrique electrique = new VoitureElectrique(2L, "250-TUN-2", "Tesla Model 3", new BigDecimal("180.00"), 15);
        assertFalse(electrique.estPretPourLocation()); // 15% < 30%
        electrique.rechargerBatterie(70);
        assertEquals(85, electrique.getNiveauBatterie());
        assertTrue(electrique.estPretPourLocation());
    }
}

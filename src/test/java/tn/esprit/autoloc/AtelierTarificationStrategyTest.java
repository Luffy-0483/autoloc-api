package tn.esprit.autoloc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tn.esprit.autoloc.pattern.strategy.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Atelier GoF & SOLID - Tests Partie C (Strategy et OCP)")
class AtelierTarificationStrategyTest {

    private TarificationService tarificationService;

    @BeforeEach
    void setUp() {
        tarificationService = new TarificationService(List.of(
                new TarificationStandard(),
                new TarificationWeekend(),
                new TarificationLongueDuree(),
                new TarificationEntreprise()
        ));
    }

    @Test
    @DisplayName("Étape 2 : TarificationStandard = tarifJournalier * jours")
    void testTarificationStandard() {
        double total = tarificationService.calculerPrix("STANDARD", 100.0, 3);
        assertEquals(300.0, total, 0.001);
    }

    @Test
    @DisplayName("Étape 2 : TarificationWeekend = 10% de réduction")
    void testTarificationWeekend() {
        // 100 * 2 = 200 -> -10% = 180
        double total = tarificationService.calculerPrix("WEEKEND", 100.0, 2);
        assertEquals(180.0, total, 0.001);
    }

    @Test
    @DisplayName("Étape 2 : TarificationLongueDuree = 20% de réduction")
    void testTarificationLongueDuree() {
        // 100 * 10 = 1000 -> -20% = 800
        double total = tarificationService.calculerPrix("LONGUE_DUREE", 100.0, 10);
        assertEquals(800.0, total, 0.001);
    }

    @Test
    @DisplayName("Étape 4 : TarificationEntreprise (nouvelle politique) = 15% de réduction")
    void testTarificationEntreprise() {
        // 100 * 4 = 400 -> -15% = 340
        double total = tarificationService.calculerPrix("ENTREPRISE", 100.0, 4);
        assertEquals(340.0, total, 0.001);
    }

    @Test
    @DisplayName("Question 4 : Un type de tarif inexistant doit lever une IllegalArgumentException")
    void testTypeTarifInexistant() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> tarificationService.calculerPrix("TARIF_INCONNU", 100.0, 3));

        assertTrue(ex.getMessage().contains("inconnu"));
    }

    @Test
    @DisplayName("Validation des paramètres : jours <= 0 ou tarif <= 0 doit lever IllegalArgumentException")
    void testParametresInvalides() {
        assertThrows(IllegalArgumentException.class, () -> tarificationService.calculerPrix("STANDARD", -10.0, 3));
        assertThrows(IllegalArgumentException.class, () -> tarificationService.calculerPrix("STANDARD", 100.0, 0));
    }
}

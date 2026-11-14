package tn.esprit.autoloc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tn.esprit.autoloc.pattern.decorator.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests du Design Pattern Decorator - TarifLocationDecorator")
class TarifLocationDecoratorTest {

    @Test
    @DisplayName("Le composant de base doit calculer tarifJournalier * nombreJours")
    void testTarifDeBase() {
        TarifLocation base = new BaseTarifLocation(new BigDecimal("100.00"), 3);

        assertEquals(new BigDecimal("300.00"), base.getPrix());
        assertTrue(base.getDescription().contains("3 jours"));
        assertTrue(base.getDescription().contains("100.00 TND/j"));
    }

    @Test
    @DisplayName("La décoration avec Assurance Tous Risques doit ajouter 25 TND/jour")
    void testAssuranceDecorator() {
        TarifLocation base = new BaseTarifLocation(new BigDecimal("100.00"), 3);
        TarifLocation avecAssurance = new AssuranceTousRisquesDecorator(base, 3);

        // 300 + (3 * 25) = 375.00
        assertEquals(new BigDecimal("375.00"), avecAssurance.getPrix());
        assertTrue(avecAssurance.getDescription().contains("Assurance Tous Risques"));
    }

    @Test
    @DisplayName("La décoration cumulée (Base + Assurance + GPS + ConducteurSupp + TVA) doit s'empiler correctement")
    void testCumulDecorateurs() {
        int jours = 4;
        BigDecimal tarifJ = new BigDecimal("80.00"); // 4 * 80 = 320.00

        TarifLocation tarif = new BaseTarifLocation(tarifJ, jours);
        // Base = 320.00

        tarif = new AssuranceTousRisquesDecorator(tarif, jours);
        // 320 + (4 * 25) = 420.00

        tarif = new GpsOptionDecorator(tarif);
        // 420 + 20 = 440.00

        tarif = new ConducteurSupplementaireDecorator(tarif, jours);
        // 440 + (4 * 15) = 500.00

        tarif = new TvaDecorator(tarif);
        // 500 + 19% (95) = 595.00

        assertEquals(new BigDecimal("595.00"), tarif.getPrix());
        String desc = tarif.getDescription();
        assertTrue(desc.contains("Tarif de base"));
        assertTrue(desc.contains("Assurance Tous Risques"));
        assertTrue(desc.contains("Option GPS"));
        assertTrue(desc.contains("Conducteur Supplémentaire"));
        assertTrue(desc.contains("TVA 19%"));
    }
}

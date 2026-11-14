package tn.esprit.autoloc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tn.esprit.autoloc.pattern.strategy.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests du Design Pattern Strategy & OCP - CalculRemiseService")
class CalculRemiseStrategyTest {

    private CalculRemiseService calculRemiseService;

    @BeforeEach
    void setUp() {
        Map<String, RemiseLocationStrategy> strategies = new HashMap<>();
        strategies.put("VIP", new VipRemiseStrategy());
        strategies.put("ETUDIANT", new EtudiantRemiseStrategy());
        strategies.put("FIDELITE", new FideliteRemiseStrategy());
        strategies.put("STANDARD", new StandardRemiseStrategy());

        calculRemiseService = new CalculRemiseService(strategies);
    }

    @Test
    @DisplayName("La stratégie VIP doit appliquer une réduction de 20%")
    void testRemiseVip() {
        BigDecimal montantInitial = new BigDecimal("100.00");
        BigDecimal montantFinal = calculRemiseService.appliquerRemise("VIP", montantInitial);

        assertEquals(new BigDecimal("80.00"), montantFinal);
    }

    @Test
    @DisplayName("La stratégie ETUDIANT doit appliquer une réduction de 15%")
    void testRemiseEtudiant() {
        BigDecimal montantInitial = new BigDecimal("200.00");
        BigDecimal montantFinal = calculRemiseService.appliquerRemise("ETUDIANT", montantInitial);

        assertEquals(new BigDecimal("170.00"), montantFinal);
    }

    @Test
    @DisplayName("La stratégie FIDELITE doit appliquer une réduction de 10%")
    void testRemiseFidelite() {
        BigDecimal montantInitial = new BigDecimal("300.00");
        BigDecimal montantFinal = calculRemiseService.appliquerRemise("FIDELITE", montantInitial);

        assertEquals(new BigDecimal("270.00"), montantFinal);
    }

    @Test
    @DisplayName("Un code inconnu ou nul doit se replier sur STANDARD (0% de remise)")
    void testRemiseStandardEtInconnue() {
        BigDecimal montantInitial = new BigDecimal("150.00");

        BigDecimal standardFinal = calculRemiseService.appliquerRemise("STANDARD", montantInitial);
        assertEquals(new BigDecimal("150.00"), standardFinal);

        BigDecimal inconnuFinal = calculRemiseService.appliquerRemise("CODE_INEXISTANT", montantInitial);
        assertEquals(new BigDecimal("150.00"), inconnuFinal);

        BigDecimal nullFinal = calculRemiseService.appliquerRemise(null, montantInitial);
        assertEquals(new BigDecimal("150.00"), nullFinal);
    }
}

package tn.esprit.autoloc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.pattern.strategy.CalculRemiseService;
import tn.esprit.autoloc.pattern.strategy.StandardRemiseStrategy;
import tn.esprit.autoloc.pattern.strategy.VipRemiseStrategy;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.service.DevisLocationServiceImpl;
import tn.esprit.autoloc.web.dto.DevisLocationDTO;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests du Service DevisLocationServiceImpl (Decorator + Strategy)")
class DevisLocationServiceImplTest {

    @Mock
    private VehiculeRepository vehiculeRepository;

    private DevisLocationServiceImpl devisService;

    @BeforeEach
    void setUp() {
        CalculRemiseService calculRemiseService = new CalculRemiseService(Map.of(
                "VIP", new VipRemiseStrategy(),
                "STANDARD", new StandardRemiseStrategy()
        ));
        devisService = new DevisLocationServiceImpl(vehiculeRepository, calculRemiseService);
    }

    @Test
    @DisplayName("Calcul de devis complet combinant options décorées et remise VIP")
    void testCalculDevisComplet() {
        Vehicule v = new Vehicule();
        v.setIdVehicule(10L);
        v.setImmatriculation("240-TUN-999");
        v.setTarifJournalier(new BigDecimal("100.00"));

        when(vehiculeRepository.findById(10L)).thenReturn(Optional.of(v));

        // 3 jours @ 100 TND = 300 base
        // Assurance = 3 * 25 = 75 -> 375
        // GPS = 20 -> 395
        // TVA 19% = 395 * 1.19 = 470.05 TTC
        // Remise VIP 20% = 470.05 * 0.8 = 376.04
        DevisLocationDTO devis = devisService.calculerDevis(10L, 3, true, true, false, "VIP");

        assertNotNull(devis);
        assertEquals(new BigDecimal("300.00"), devis.getMontantBase());
        assertEquals(new BigDecimal("470.05"), devis.getMontantTotalTTC());
        assertEquals("VIP", devis.getCodeRemiseApplique());
        assertEquals(new BigDecimal("376.04"), devis.getMontantApresRemise());
        assertTrue(devis.getDetailCalcul().contains("Assurance"));
        assertTrue(devis.getDetailCalcul().contains("GPS"));
    }
}

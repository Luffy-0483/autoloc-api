package tn.esprit.autoloc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.autoloc.pattern.factory.PaiementProcessorFactory;
import tn.esprit.autoloc.pattern.strategy.CalculRemiseService;
import tn.esprit.autoloc.service.IClientService;
import tn.esprit.autoloc.service.IVehiculeService;
import tn.esprit.autoloc.service.isp.IVehiculeConsultationService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Tests du Pattern Singleton, DIP et ISP dans l'écosystème Spring Boot")
class SpringSingletonTest {

    @Autowired
    private IClientService clientService1;

    @Autowired
    private IClientService clientService2;

    @Autowired
    private CalculRemiseService remiseService1;

    @Autowired
    private CalculRemiseService remiseService2;

    @Autowired
    private PaiementProcessorFactory paiementFactory;

    @Autowired
    private IVehiculeConsultationService vehiculeConsultationService;

    @Autowired
    private IVehiculeService vehiculeService;

    @Test
    @DisplayName("Le conteneur Spring garantit le scope Singleton par défaut pour les Beans injectés")
    void testSingletonScope() {
        assertNotNull(clientService1);
        assertNotNull(clientService2);
        assertSame(clientService1, clientService2, "Deux injections du même service doivent référencer la même instance Singleton");

        assertNotNull(remiseService1);
        assertNotNull(remiseService2);
        assertSame(remiseService1, remiseService2, "CalculRemiseService doit être un Singleton");
    }

    @Test
    @DisplayName("L'injection de dépendances (DIP) et la ségrégation (ISP) fonctionnent harmonieusement")
    void testDIPAndISP() {
        assertNotNull(paiementFactory);
        assertNotNull(vehiculeConsultationService);
        assertNotNull(vehiculeService);

        // L'interface ségréguée est résolue par la même instance sous-jacente du bean VehiculeServiceImpl
        assertSame(vehiculeConsultationService, vehiculeService);
    }
}

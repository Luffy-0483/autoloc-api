package tn.esprit.autoloc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tn.esprit.autoloc.domain.ModePaiement;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.pattern.factory.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests du Design Pattern Factory - PaiementProcessorFactory")
class PaiementProcessorFactoryTest {

    private PaiementProcessorFactory factory;
    private CartePaiementProcessor carteProcessor;
    private EspecesPaiementProcessor especesProcessor;
    private VirementPaiementProcessor virementProcessor;
    private PayPalPaiementProcessor payPalProcessor;

    @BeforeEach
    void setUp() {
        carteProcessor = new CartePaiementProcessor();
        especesProcessor = new EspecesPaiementProcessor();
        virementProcessor = new VirementPaiementProcessor();
        payPalProcessor = new PayPalPaiementProcessor();

        factory = new PaiementProcessorFactory(List.of(
                carteProcessor, especesProcessor, virementProcessor, payPalProcessor
        ));
    }

    @Test
    @DisplayName("La Factory doit retourner CartePaiementProcessor pour ModePaiement.CARTE")
    void testGetProcessorCarte() {
        PaiementProcessor processor = factory.getProcessor(ModePaiement.CARTE);
        assertNotNull(processor);
        assertInstanceOf(CartePaiementProcessor.class, processor);

        Paiement p = new Paiement();
        p.setIdPaiement(1L);
        p.setMontant(new BigDecimal("150.00"));
        p.setDatePaiement(LocalDate.now());
        p.setModePaiement(ModePaiement.CARTE);

        assertDoesNotThrow(() -> processor.traiterPaiement(p));
        assertTrue(processor.getDetailsTransaction(p).contains("Carte Bancaire"));
    }

    @Test
    @DisplayName("La Factory doit retourner PayPalPaiementProcessor pour ModePaiement.PAYPAL")
    void testGetProcessorPayPal() {
        PaiementProcessor processor = factory.getProcessor(ModePaiement.PAYPAL);
        assertNotNull(processor);
        assertInstanceOf(PayPalPaiementProcessor.class, processor);

        Paiement p = new Paiement();
        p.setIdPaiement(2L);
        p.setMontant(new BigDecimal("220.00"));
        p.setDatePaiement(LocalDate.now());
        p.setModePaiement(ModePaiement.PAYPAL);

        assertDoesNotThrow(() -> processor.traiterPaiement(p));
        assertTrue(processor.getDetailsTransaction(p).contains("PayPal"));
    }

    @Test
    @DisplayName("La Factory doit lever IllegalArgumentException pour un mode non enregistré")
    void testGetProcessorNonEnregistre() {
        PaiementProcessorFactory videFactory = new PaiementProcessorFactory(List.of());
        assertThrows(IllegalArgumentException.class, () -> videFactory.getProcessor(ModePaiement.CARTE));
    }
}

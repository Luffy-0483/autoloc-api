package tn.esprit.autoloc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.service.VehiculeServiceImpl;
import tn.esprit.autoloc.service.journalisation.JournalisationService;
import tn.esprit.autoloc.service.validation.VehiculeValidator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Atelier GoF & SOLID - Tests SRP et DIP sur VehiculeService")
class VehiculeServiceSrpTest {

    @Mock
    private VehiculeRepository vehiculeRepository;

    @Mock
    private AgenceRepository agenceRepository;

    private VehiculeValidator vehiculeValidator;
    private JournalisationService journalisationService;
    private VehiculeServiceImpl vehiculeService;

    @BeforeEach
    void setUp() {
        vehiculeValidator = new VehiculeValidator();
        journalisationService = mock(JournalisationService.class);
        vehiculeService = new VehiculeServiceImpl(vehiculeRepository, agenceRepository, vehiculeValidator, journalisationService);
    }

    @Test
    @DisplayName("La méthode ajouter() doit valider le tarif et journaliser avant d'enregistrer")
    void testAjouterVehiculeValide() {
        Vehicule v = new Vehicule();
        v.setImmatriculation("230-TUN-1234");
        v.setTarifJournalier(new BigDecimal("90.00"));

        when(vehiculeRepository.save(any(Vehicule.class))).thenReturn(v);

        Vehicule resultat = vehiculeService.ajouter(v);

        assertNotNull(resultat);
        verify(journalisationService, times(1)).journaliser(contains("Ajout du véhicule"));
        verify(vehiculeRepository, times(1)).save(v);
    }

    @Test
    @DisplayName("Un tarif journalier négatif ou nul doit être rejeté par VehiculeValidator avec l'exception attendue")
    void testAjouterVehiculeTarifInvalide() {
        Vehicule v = new Vehicule();
        v.setImmatriculation("230-TUN-0000");
        v.setTarifJournalier(new BigDecimal("0.00"));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> vehiculeService.ajouter(v));

        assertEquals("Le tarif doit être positif", ex.getMessage());
        verify(vehiculeRepository, never()).save(any());
        verify(journalisationService, never()).journaliser(any());
    }

    @Test
    @DisplayName("calculerTarif doit lever une exception si le nombre de jours est <= 0")
    void testCalculerTarifNombreJoursInvalide() {
        Vehicule v = new Vehicule();
        v.setTarifJournalier(new BigDecimal("100.00"));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> vehiculeService.calculerTarif(v, 0));

        assertEquals("Le nombre de jours doit être positif", ex.getMessage());
    }

    @Test
    @DisplayName("calculerTarif doit retourner tarifJournalier * nombreJours")
    void testCalculerTarifValide() {
        Vehicule v = new Vehicule();
        v.setTarifJournalier(new BigDecimal("100.00"));

        double total = vehiculeService.calculerTarif(v, 5);
        assertEquals(500.0, total);
    }
}

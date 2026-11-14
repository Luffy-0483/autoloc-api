package tn.esprit.autoloc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.validation.ReservationValidator;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests du Principe SOLID SRP - ReservationValidator")
class ReservationValidatorTest {

    private ReservationValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ReservationValidator();
    }

    @Test
    @DisplayName("Des dates valides (début <= fin) doivent être acceptées")
    void testDatesValides() {
        LocalDate debut = LocalDate.of(2026, 11, 20);
        LocalDate fin = LocalDate.of(2026, 11, 25);

        assertDoesNotThrow(() -> validator.validerDates(debut, fin));
    }

    @Test
    @DisplayName("Une date de fin antérieure à la date de début doit lever IllegalArgumentException")
    void testDateFinAvantDateDebut() {
        LocalDate debut = LocalDate.of(2026, 11, 25);
        LocalDate fin = LocalDate.of(2026, 11, 20);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validerDates(debut, fin));
        assertTrue(ex.getMessage().contains("antérieure"));
    }

    @Test
    @DisplayName("Un véhicule en MAINTENANCE doit lever IllegalStateException")
    void testVehiculeEnMaintenance() {
        Vehicule v = new Vehicule();
        v.setImmatriculation("250-TUN-100");
        v.setStatut(StatutVehicule.MAINTENANCE);

        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> validator.validerDisponibiliteVehicule(v));
        assertTrue(ex.getMessage().contains("maintenance"));
    }

    @Test
    @DisplayName("Un véhicule DISPONIBLE doit être accepté sans exception")
    void testVehiculeDisponible() {
        Vehicule v = new Vehicule();
        v.setImmatriculation("250-TUN-200");
        v.setStatut(StatutVehicule.DISPONIBLE);

        assertDoesNotThrow(() -> validator.validerDisponibiliteVehicule(v));
    }
}

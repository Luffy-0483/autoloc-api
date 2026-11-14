package tn.esprit.autoloc.service.validation;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;

import java.time.LocalDate;

/**
 * SOLID SRP (Single Responsibility Principle).
 * Responsabilité unique : Valider les règles métier relatives aux réservations de véhicules.
 * Isole la logique de validation de la persistance (DAO/Repository) et du contrôleur HTTP.
 */
@Component
public class ReservationValidator {

    /**
     * Valide l'intégrité temporelle d'une réservation.
     */
    public void validerDates(LocalDate dateDebut, LocalDate dateFin) {
        if (dateDebut == null || dateFin == null) {
            throw new IllegalArgumentException("Les dates de début et de fin de réservation sont obligatoires.");
        }
        if (dateFin.isBefore(dateDebut)) {
            throw new IllegalArgumentException("La date de fin (" + dateFin + ") ne peut pas être antérieure à la date de début (" + dateDebut + ").");
        }
    }

    /**
     * Valide l'éligibilité d'un véhicule pour une nouvelle réservation.
     */
    public void validerDisponibiliteVehicule(Vehicule vehicule) {
        if (vehicule == null) {
            throw new IllegalArgumentException("Le véhicule assigné ne peut pas être null.");
        }
        if (vehicule.getStatut() == StatutVehicule.MAINTENANCE) {
            throw new IllegalStateException("Le véhicule immatriculé " + vehicule.getImmatriculation() + " est actuellement en maintenance.");
        }
    }

    /**
     * Validation complète d'une réservation.
     */
    public void validerReservation(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("La réservation ne peut pas être null.");
        }
        validerDates(reservation.getDateDebut(), reservation.getDateFin());
        if (reservation.getVehicule() != null) {
            validerDisponibiliteVehicule(reservation.getVehicule());
        }
    }
}

package tn.esprit.autoloc.pattern.observer;

import tn.esprit.autoloc.domain.Reservation;

/**
 * GoF Observer Pattern - Interface Observateur (Observer).
 * Réagit aux notifications émises par le sujet lorsqu'une réservation change d'état.
 */
public interface ReservationObserver {

    /**
     * Méthode de rappel (callback) invoquée lors d'un événement sur la réservation.
     *
     * @param reservation la réservation concernée
     * @param evenement description de l'événement (ex: CREATION, CONFIRMATION, ANNULATION)
     */
    void update(Reservation reservation, String evenement);

    /**
     * Canal de communication représenté par cet observateur (EMAIL, SMS, AUDIT, etc.).
     */
    String getCanal();
}

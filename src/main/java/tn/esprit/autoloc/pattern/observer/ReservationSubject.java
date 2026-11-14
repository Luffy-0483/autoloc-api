package tn.esprit.autoloc.pattern.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Reservation;

import java.util.ArrayList;
import java.util.List;

/**
 * GoF Observer Pattern - Sujet (Subject).
 * Maintient la liste des observateurs et les notifie de tout changement d'état sur une réservation.
 */
@Slf4j
@Component
public class ReservationSubject {

    private final List<ReservationObserver> observers = new ArrayList<>();

    public ReservationSubject(List<ReservationObserver> initialObservers) {
        if (initialObservers != null) {
            this.observers.addAll(initialObservers);
        }
        log.info("[OBSERVER] ReservationSubject configuré avec {} observateurs", observers.size());
    }

    public void attacher(ReservationObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void detacher(ReservationObserver observer) {
        observers.remove(observer);
    }

    public void notifierObservateurs(Reservation reservation, String evenement) {
        log.info("[OBSERVER] Notification des {} observateurs pour l'événement '{}' (réservation id={})",
                observers.size(), evenement, reservation != null ? reservation.getIdReservation() : "null");
        for (ReservationObserver observer : observers) {
            try {
                observer.update(reservation, evenement);
            } catch (Exception e) {
                log.error("[OBSERVER] Échec de la notification sur canal {} : {}", observer.getCanal(), e.getMessage());
            }
        }
    }

    public List<ReservationObserver> getObservers() {
        return List.copyOf(observers);
    }
}

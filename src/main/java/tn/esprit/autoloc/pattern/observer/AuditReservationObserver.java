package tn.esprit.autoloc.pattern.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Reservation;

/**
 * Observateur Concret : Journalisation et audit de sécurité des réservations.
 */
@Slf4j
@Component
public class AuditReservationObserver implements ReservationObserver {

    @Override
    public void update(Reservation reservation, String evenement) {
        log.info("[OBSERVER - AUDIT] Journalisation de l'événement '{}' - Réservation id={} - Statut={}",
                evenement, reservation.getIdReservation(), reservation.getStatut());
    }

    @Override
    public String getCanal() {
        return "AUDIT";
    }
}

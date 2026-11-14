package tn.esprit.autoloc.pattern.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Reservation;

/**
 * Observateur Concret : Notification par Email au client et à l'agence.
 */
@Slf4j
@Component
public class EmailReservationObserver implements ReservationObserver {

    @Override
    public void update(Reservation reservation, String evenement) {
        String clientEmail = (reservation.getClient() != null) ? reservation.getClient().getEmail() : "inconnu";
        log.info("[OBSERVER - EMAIL] Envoi d'un email à '{}' : Événement='{}' pour réservation id={}",
                clientEmail, evenement, reservation.getIdReservation());
    }

    @Override
    public String getCanal() {
        return "EMAIL";
    }
}

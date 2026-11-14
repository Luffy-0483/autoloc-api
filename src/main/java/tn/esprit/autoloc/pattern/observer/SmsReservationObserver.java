package tn.esprit.autoloc.pattern.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Reservation;

/**
 * Observateur Concret : Notification par SMS instantané.
 */
@Slf4j
@Component
public class SmsReservationObserver implements ReservationObserver {

    @Override
    public void update(Reservation reservation, String evenement) {
        String clientTel = (reservation.getClient() != null) ? reservation.getClient().getTelephone() : "inconnu";
        log.info("[OBSERVER - SMS] Envoi d'un SMS au '{}' : AutoLoc alerte='{}' pour réservation id={}",
                clientTel, evenement, reservation.getIdReservation());
    }

    @Override
    public String getCanal() {
        return "SMS";
    }
}

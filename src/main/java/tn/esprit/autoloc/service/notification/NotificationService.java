package tn.esprit.autoloc.service.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Reservation;

/**
 * SOLID SRP (Single Responsibility Principle).
 * Responsabilité unique : Formater et orchestrer l'envoi de messages de notification.
 */
@Slf4j
@Service
public class NotificationService {

    public void notifierCreationReservation(Reservation reservation) {
        Client client = reservation.getClient();
        String destinataire = (client != null) ? client.getPrenom() + " " + client.getNom() : "Client";
        log.info("[NOTIFICATION] Notification émise pour {} : Votre réservation n°{} du {} au {} a été enregistrée.",
                destinataire, reservation.getIdReservation(), reservation.getDateDebut(), reservation.getDateFin());
    }

    public void notifierChangementStatut(Reservation reservation) {
        log.info("[NOTIFICATION] Le statut de la réservation n°{} a été mis à jour vers '{}'.",
                reservation.getIdReservation(), reservation.getStatut());
    }
}

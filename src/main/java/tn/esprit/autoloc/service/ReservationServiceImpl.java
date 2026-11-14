package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.pattern.observer.ReservationSubject;
import tn.esprit.autoloc.repository.ClientRepository;
import tn.esprit.autoloc.repository.ReservationRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.service.notification.NotificationService;
import tn.esprit.autoloc.service.validation.ReservationValidator;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.util.List;

/**
 * Service de gestion des réservations.
 * Refactorisé pour respecter :
 * - SOLID SRP : délégation de la validation à ReservationValidator et de la notification à NotificationService.
 * - GoF Observer : publication d'événements vers ReservationSubject (notifiant email, sms, audit sans couplage fort).
 * - SOLID DIP : dépendances injectées par constructeur sur des composants abstraits / gérés par Spring.
 */
@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;
    private final ReservationValidator reservationValidator;
    private final ReservationSubject reservationSubject;
    private final NotificationService notificationService;

    @Override
    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation retrieveReservation(Long idReservation) {
        return reservationRepository.findById(idReservation)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation introuvable avec l'id : " + idReservation));
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        reservationValidator.validerReservation(reservation);
        Reservation saved = reservationRepository.save(reservation);
        reservationSubject.notifierObservateurs(saved, "RESERVATION_CREEE");
        notificationService.notifierCreationReservation(saved);
        return saved;
    }

    @Override
    public void removeReservation(Long idReservation) {
        if (!reservationRepository.existsById(idReservation)) {
            throw new ResourceNotFoundException("Impossible de supprimer : Reservation introuvable avec l'id : " + idReservation);
        }
        reservationRepository.deleteById(idReservation);
    }

    @Override
    public Reservation modifyReservation(Reservation reservation) {
        reservationValidator.validerReservation(reservation);
        Reservation saved = reservationRepository.save(reservation);
        reservationSubject.notifierObservateurs(saved, "STATUT_MODIFIE");
        notificationService.notifierChangementStatut(saved);
        return saved;
    }

    @Override
    public Reservation creerReservationEtAffecter(Reservation reservation, Long idClient, Long idVehicule) {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable avec l'id : " + idClient));
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule introuvable avec l'id : " + idVehicule));

        reservationValidator.validerDisponibiliteVehicule(vehicule);
        reservationValidator.validerDates(reservation.getDateDebut(), reservation.getDateFin());

        reservation.setClient(client);
        reservation.setVehicule(vehicule);

        Reservation saved = reservationRepository.save(reservation);
        reservationSubject.notifierObservateurs(saved, "RESERVATION_AFFECTEE");
        notificationService.notifierCreationReservation(saved);
        return saved;
    }
}

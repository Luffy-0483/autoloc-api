package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;

import java.util.List;

public interface IReservationService {

    List<Reservation> retrieveAllReservations();

    Reservation retrieveReservation(Long idReservation);

    Reservation addReservation(Reservation reservation);

    void removeReservation(Long idReservation);

    Reservation modifyReservation(Reservation reservation);

    Reservation creerReservationEtAffecter(Reservation reservation, Long idClient, Long idVehicule);
}

package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@Tag(name = "Gestion des Réservations", description = "Endpoints de gestion des réservations de véhicules par les clients")
@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationRestController {

    private final IReservationService reservationService;

    // http://localhost:8089/gestionprojets/reservation/retrieve-all-reservations
    @Operation(summary = "Récupérer toutes les réservations", description = "Retourne l'ensemble des réservations enregistrées")
    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> getReservations() {
        return reservationService.retrieveAllReservations();
    }

    // http://localhost:8089/gestionprojets/reservation/retrieve-reservation/1
    @Operation(summary = "Récupérer une réservation par ID", description = "Retourne les informations d'une réservation spécifique")
    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") Long reservationId) {
        return reservationService.retrieveReservation(reservationId);
    }

    // http://localhost:8089/gestionprojets/reservation/add-reservation
    @Operation(summary = "Ajouter une réservation", description = "Crée une nouvelle réservation simple")
    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    // http://localhost:8089/gestionprojets/reservation/modify-reservation
    @Operation(summary = "Modifier une réservation", description = "Met à jour une réservation existante")
    @PutMapping("/modify-reservation")
    public Reservation modifyReservation(@RequestBody Reservation reservation) {
        return reservationService.modifyReservation(reservation);
    }

    // http://localhost:8089/gestionprojets/reservation/remove-reservation/1
    @Operation(summary = "Supprimer une réservation", description = "Supprime une réservation selon son identifiant")
    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") Long reservationId) {
        reservationService.removeReservation(reservationId);
    }

    // http://localhost:8089/gestionprojets/reservation/creer-reservation-affecter/1/2
    @Operation(summary = "Créer une réservation et l'affecter", description = "Crée la réservation et l'affecte simultanément au client et au véhicule")
    @PostMapping("/creer-reservation-affecter/{client-id}/{vehicule-id}")
    public Reservation creerReservationEtAffecter(@RequestBody Reservation reservation,
                                                  @PathVariable("client-id") Long clientId,
                                                  @PathVariable("vehicule-id") Long vehiculeId) {
        return reservationService.creerReservationEtAffecter(reservation, clientId, vehiculeId);
    }
}

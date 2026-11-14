package tn.esprit.autoloc.service.isp;

import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

/**
 * SOLID ISP (Interface Segregation Principle).
 * Interface ségréguée réservée aux opérations de lecture / consultation du catalogue de véhicules.
 */
public interface IVehiculeConsultationService {

    List<Vehicule> retrieveAllVehicules();

    Vehicule retrieveVehicule(Long idVehicule);

    /**
     * Méthode conforme à l'atelier AutoLoc (Partie A & B).
     */
    double calculerTarif(Vehicule vehicule, int nombreJours);
}

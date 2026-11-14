package tn.esprit.autoloc.service.isp;

import tn.esprit.autoloc.domain.Vehicule;

/**
 * SOLID ISP (Interface Segregation Principle).
 * Interface ségréguée dédiée aux opérations d'administration et de gestion du parc de véhicules.
 */
public interface IVehiculeGestionService {

    Vehicule addVehicule(Vehicule vehicule);

    /**
     * Méthode conforme à l'atelier AutoLoc (Partie A & B).
     */
    Vehicule ajouter(Vehicule vehicule);

    Vehicule modifyVehicule(Vehicule vehicule);

    void removeVehicule(Long idVehicule);
}

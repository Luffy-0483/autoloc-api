package tn.esprit.autoloc.service.isp.atelier;

import tn.esprit.autoloc.domain.Vehicule;

/**
 * Atelier GoF & SOLID - Partie D (Situation 2 : ISP).
 * Contrat spécialisé : opérations d'écriture et de gestion du cycle de vie du véhicule.
 */
public interface ModificationVehicule {

    Vehicule ajouter(Vehicule vehicule);

    Vehicule modifier(Long id, Vehicule vehicule);

    void supprimer(Long id);
}

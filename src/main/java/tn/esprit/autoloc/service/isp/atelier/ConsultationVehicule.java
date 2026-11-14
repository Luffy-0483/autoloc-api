package tn.esprit.autoloc.service.isp.atelier;

import tn.esprit.autoloc.domain.Vehicule;

/**
 * Atelier GoF & SOLID - Partie D (Situation 2 : ISP).
 * Contrat spécialisé : uniquement la consultation d'un véhicule.
 */
public interface ConsultationVehicule {

    Vehicule consulter(Long id);
}

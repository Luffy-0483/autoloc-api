package tn.esprit.autoloc.service.isp;

/**
 * SOLID ISP (Interface Segregation Principle).
 * Interface ségréguée dédiée à l'affectation organisationnelle d'un véhicule à une agence.
 */
public interface IVehiculeAffectationService {

    void affecterVehiculeAAgence(Long idVehicule, Long idAgence);
}

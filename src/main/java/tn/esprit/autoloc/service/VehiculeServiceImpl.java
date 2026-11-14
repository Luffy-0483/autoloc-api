package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.service.journalisation.JournalisationService;
import tn.esprit.autoloc.service.validation.VehiculeValidator;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.util.List;

/**
 * Service de gestion des véhicules.
 * Refactorisé selon l'Atelier GoF & SOLID :
 * - SOLID SRP : délégation de la validation à VehiculeValidator et de la journalisation à JournalisationService.
 * - SOLID DIP : dépendance vers l'interface d'abstraction VehiculeRepository injectée par Spring.
 * - SOLID ISP : implémente les interfaces ségréguées de consultation, gestion et affectation.
 */
@Service
@AllArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;
    private final VehiculeValidator vehiculeValidator;
    private final JournalisationService journalisationService;

    @Override
    public List<Vehicule> retrieveAllVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule retrieveVehicule(Long idVehicule) {
        return vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule introuvable avec l'id : " + idVehicule));
    }

    @Override
    public Vehicule addVehicule(Vehicule vehicule) {
        return ajouter(vehicule);
    }

    /**
     * Méthode refactorisée selon l'Atelier Partie B.1 (SRP & DIP).
     * Ne cumule plus la validation métier ni la journalisation directement.
     */
    @Override
    public Vehicule ajouter(Vehicule vehicule) {
        // 1. Délégation de la validation métier (SRP)
        vehiculeValidator.validerTarif(vehicule);

        // 2. Délégation de la journalisation (SRP)
        journalisationService.journaliser("Ajout du véhicule immatriculé : " + vehicule.getImmatriculation());

        // 3. Persistance via l'abstraction repository (DIP)
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public double calculerTarif(Vehicule vehicule, int nombreJours) {
        vehiculeValidator.validerNombreJours(nombreJours);
        if (vehicule == null || vehicule.getTarifJournalier() == null) {
            throw new IllegalArgumentException("Véhicule invalide pour le calcul du tarif");
        }
        return vehicule.getTarifJournalier().doubleValue() * nombreJours;
    }

    @Override
    public void removeVehicule(Long idVehicule) {
        if (!vehiculeRepository.existsById(idVehicule)) {
            throw new ResourceNotFoundException("Impossible de supprimer : Vehicule introuvable avec l'id : " + idVehicule);
        }
        journalisationService.journaliser("Suppression du véhicule id : " + idVehicule);
        vehiculeRepository.deleteById(idVehicule);
    }

    @Override
    public Vehicule modifyVehicule(Vehicule vehicule) {
        vehiculeValidator.validerTarif(vehicule);
        journalisationService.journaliser("Modification du véhicule id : " + vehicule.getIdVehicule());
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public void affecterVehiculeAAgence(Long idVehicule, Long idAgence) {
        Vehicule vehicule = retrieveVehicule(idVehicule);
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException("Agence introuvable avec l'id : " + idAgence));
        vehicule.setAgence(agence);
        vehiculeRepository.save(vehicule);
        journalisationService.journaliser("Affectation du véhicule " + idVehicule + " à l'agence " + idAgence);
    }
}

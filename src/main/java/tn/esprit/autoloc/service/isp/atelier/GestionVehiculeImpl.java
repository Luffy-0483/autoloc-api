package tn.esprit.autoloc.service.isp.atelier;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

/**
 * Implémentation du service GestionVehicule respectant ISP.
 */
@Slf4j
@Service
@AllArgsConstructor
public class GestionVehiculeImpl implements GestionVehicule {

    private final VehiculeRepository vehiculeRepository;

    @Override
    public Vehicule consulter(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Véhicule introuvable id: " + id));
    }

    @Override
    public Vehicule ajouter(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule modifier(Long id, Vehicule vehicule) {
        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Véhicule introuvable id: " + id);
        }
        vehicule.setIdVehicule(id);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public void supprimer(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Véhicule introuvable id: " + id);
        }
        vehiculeRepository.deleteById(id);
    }

    @Override
    public void exporterRapport() {
        log.info("[RAPPORT] Export du rapport global de l'état du parc de véhicules généré.");
    }

    @Override
    public void envoyerNotification() {
        log.info("[NOTIFICATION] Notification émise aux gestionnaires de parc.");
    }
}

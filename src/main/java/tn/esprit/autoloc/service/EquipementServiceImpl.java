package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EquipementServiceImpl implements IEquipementService {

    private final EquipementRepository equipementRepository;
    private final VehiculeRepository vehiculeRepository;

    @Override
    public List<Equipement> retrieveAllEquipements() {
        return equipementRepository.findAll();
    }

    @Override
    public Equipement retrieveEquipement(Long idEquipement) {
        return equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement introuvable avec l'id : " + idEquipement));
    }

    @Override
    public Equipement addEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    public void removeEquipement(Long idEquipement) {
        if (!equipementRepository.existsById(idEquipement)) {
            throw new ResourceNotFoundException("Impossible de supprimer : Equipement introuvable avec l'id : " + idEquipement);
        }
        equipementRepository.deleteById(idEquipement);
    }

    @Override
    public Equipement modifyEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    public void affecterEquipementAVehicule(Long idEquipement, Long idVehicule) {
        Equipement equipement = retrieveEquipement(idEquipement);
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule introuvable avec l'id : " + idVehicule));

        if (vehicule.getEquipements() == null) {
            vehicule.setEquipements(new ArrayList<>());
        }
        if (!vehicule.getEquipements().contains(equipement)) {
            vehicule.getEquipements().add(equipement);
            vehiculeRepository.save(vehicule);
        }
    }
}

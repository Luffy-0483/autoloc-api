package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;

import java.util.List;

public interface IEquipementService {

    List<Equipement> retrieveAllEquipements();

    Equipement retrieveEquipement(Long idEquipement);

    Equipement addEquipement(Equipement equipement);

    void removeEquipement(Long idEquipement);

    Equipement modifyEquipement(Equipement equipement);

    void affecterEquipementAVehicule(Long idEquipement, Long idVehicule);
}

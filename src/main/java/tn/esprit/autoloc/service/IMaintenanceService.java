package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {

    List<Maintenance> retrieveAllMaintenances();

    Maintenance retrieveMaintenance(Long idMaintenance);

    Maintenance addMaintenance(Maintenance maintenance);

    void removeMaintenance(Long idMaintenance);

    Maintenance modifyMaintenance(Maintenance maintenance);

    void affecterMaintenanceAVehicule(Long idMaintenance, Long idVehicule);
}

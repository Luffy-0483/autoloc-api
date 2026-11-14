package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface IAgenceService {

    List<Agence> retrieveAllAgences();

    Agence retrieveAgence(Long idAgence);

    Agence addAgence(Agence agence);

    void removeAgence(Long idAgence);

    Agence modifyAgence(Agence agence);

    void affecterEmployeAAgence(Long idEmploye, Long idAgence);
}

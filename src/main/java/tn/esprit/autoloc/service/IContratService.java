package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;

import java.util.List;

public interface IContratService {

    List<Contrat> retrieveAllContrats();

    Contrat retrieveContrat(Long idContrat);

    Contrat addContrat(Contrat contrat);

    void removeContrat(Long idContrat);

    Contrat modifyContrat(Contrat contrat);

    void affecterContratAReservation(Long idContrat, Long idReservation);
}

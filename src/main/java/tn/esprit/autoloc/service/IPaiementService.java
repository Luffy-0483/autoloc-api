package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;

public interface IPaiementService {

    List<Paiement> retrieveAllPaiements();

    Paiement retrievePaiement(Long idPaiement);

    Paiement addPaiement(Paiement paiement);

    void removePaiement(Long idPaiement);

    Paiement modifyPaiement(Paiement paiement);

    Paiement ajouterPaiementEtAffecterAContrat(Paiement paiement, Long idContrat);

    String traiterPaiementViaFactory(Long idPaiement);
}

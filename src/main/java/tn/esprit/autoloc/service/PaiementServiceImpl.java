package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.pattern.factory.PaiementProcessor;
import tn.esprit.autoloc.pattern.factory.PaiementProcessorFactory;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.repository.PaiementRepository;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final PaiementRepository paiementRepository;
    private final ContratRepository contratRepository;
    private final PaiementProcessorFactory paiementProcessorFactory;

    @Override
    public List<Paiement> retrieveAllPaiements() {
        return paiementRepository.findAll();
    }

    @Override
    public Paiement retrievePaiement(Long idPaiement) {
        return paiementRepository.findById(idPaiement)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement introuvable avec l'id : " + idPaiement));
    }

    @Override
    public Paiement addPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public void removePaiement(Long idPaiement) {
        if (!paiementRepository.existsById(idPaiement)) {
            throw new ResourceNotFoundException("Impossible de supprimer : Paiement introuvable avec l'id : " + idPaiement);
        }
        paiementRepository.deleteById(idPaiement);
    }

    @Override
    public Paiement modifyPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement ajouterPaiementEtAffecterAContrat(Paiement paiement, Long idContrat) {
        Contrat contrat = contratRepository.findById(idContrat)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat introuvable avec l'id : " + idContrat));
        paiement.setContrat(contrat);
        return paiementRepository.save(paiement);
    }

    @Override
    public String traiterPaiementViaFactory(Long idPaiement) {
        Paiement paiement = retrievePaiement(idPaiement);
        PaiementProcessor processor = paiementProcessorFactory.getProcessor(paiement.getModePaiement());
        processor.traiterPaiement(paiement);
        return processor.getDetailsTransaction(paiement);
    }
}

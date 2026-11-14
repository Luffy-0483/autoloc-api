package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EmployeRepository;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final AgenceRepository agenceRepository;
    private final EmployeRepository employeRepository;

    @Override
    public List<Agence> retrieveAllAgences() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence retrieveAgence(Long idAgence) {
        return agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException("Agence introuvable avec l'id : " + idAgence));
    }

    @Override
    public Agence addAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public void removeAgence(Long idAgence) {
        if (!agenceRepository.existsById(idAgence)) {
            throw new ResourceNotFoundException("Impossible de supprimer : Agence introuvable avec l'id : " + idAgence);
        }
        agenceRepository.deleteById(idAgence);
    }

    @Override
    public Agence modifyAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public void affecterEmployeAAgence(Long idEmploye, Long idAgence) {
        Agence agence = retrieveAgence(idAgence);
        Employe employe = employeRepository.findById(idEmploye)
                .orElseThrow(() -> new ResourceNotFoundException("Employe introuvable avec l'id : " + idEmploye));
        employe.setAgence(agence);
        employeRepository.save(employe);
    }
}

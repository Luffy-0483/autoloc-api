package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;

public interface IEmployeService {

    List<Employe> retrieveAllEmployes();

    Employe retrieveEmploye(Long idEmploye);

    Employe addEmploye(Employe employe);

    void removeEmploye(Long idEmploye);

    Employe modifyEmploye(Employe employe);
}

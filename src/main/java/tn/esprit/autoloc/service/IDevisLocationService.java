package tn.esprit.autoloc.service;

import tn.esprit.autoloc.web.dto.DevisLocationDTO;

import java.math.BigDecimal;

/**
 * Service orchestrant le calcul de devis via les patterns Decorator et Strategy.
 */
public interface IDevisLocationService {

    DevisLocationDTO calculerDevis(Long idVehicule,
                                  int nombreJours,
                                  boolean avecAssuranceTousRisques,
                                  boolean avecGps,
                                  boolean avecConducteurSupp,
                                  String codeRemise);
}

package tn.esprit.autoloc.service;

import tn.esprit.autoloc.service.isp.IVehiculeAffectationService;
import tn.esprit.autoloc.service.isp.IVehiculeConsultationService;
import tn.esprit.autoloc.service.isp.IVehiculeGestionService;

/**
 * Interface agrégée pour la gestion complète des véhicules.
 * Conforme à ISP (Interface Segregation Principle) : elle hérite des 3 interfaces spécialisées
 * (Consultation, Gestion, Affectation) permettant ainsi aux clients de ne dépendre
 * que du contrat strict nécessaire à leur besoin.
 */
public interface IVehiculeService extends IVehiculeConsultationService, IVehiculeGestionService, IVehiculeAffectationService {
}

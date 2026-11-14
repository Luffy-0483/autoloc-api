package tn.esprit.autoloc.service.isp.atelier;

/**
 * Atelier GoF & SOLID - Partie D (Situation 2 : ISP).
 * Interface globale combinée.
 * Grâce au principe de ségrégation des interfaces (ISP), cette interface
 * hérite des 4 contrats spécialisés au lieu d'être un monolithe rigide.
 * Ainsi, un contrôleur ayant besoin uniquement de consulter n'injectera
 * que ConsultationVehicule sans être couplé aux 5 autres méthodes.
 */
public interface GestionVehicule extends ConsultationVehicule, ModificationVehicule, RapportVehicule, NotificationVehicule {
}

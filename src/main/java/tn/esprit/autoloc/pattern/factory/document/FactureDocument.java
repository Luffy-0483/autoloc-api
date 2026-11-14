package tn.esprit.autoloc.pattern.factory.document;

/**
 * Atelier GoF & SOLID - Partie D (Situation 1 : Factory).
 * Interface produit pour la génération de documents de facturation.
 */
public interface FactureDocument {

    /**
     * Génère le document de facturation pour le contrat spécifié.
     *
     * @param idContrat identifiant du contrat de location
     * @param montant montant facturé
     * @return contenu textuel / chemin du fichier généré
     */
    String generer(Long idContrat, double montant);

    /**
     * Format du document (ex: PDF, EXCEL).
     */
    String getFormat();
}

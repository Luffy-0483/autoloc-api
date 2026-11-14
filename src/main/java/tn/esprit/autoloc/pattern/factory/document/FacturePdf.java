package tn.esprit.autoloc.pattern.factory.document;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Produit concret : Facture au format PDF.
 */
@Slf4j
@Component("FACTURE_PDF")
public class FacturePdf implements FactureDocument {

    @Override
    public String generer(Long idContrat, double montant) {
        log.info("[DOCUMENT - PDF] Génération du PDF pour contrat n°{} (Montant: {} TND)", idContrat, montant);
        return "DOCUMENT_PDF[Contrat=" + idContrat + ", Montant=" + montant + " TND, Format=application/pdf]";
    }

    @Override
    public String getFormat() {
        return "PDF";
    }
}

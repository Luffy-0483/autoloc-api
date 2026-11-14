package tn.esprit.autoloc.pattern.factory.document;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Produit concret : Facture au format Excel (tableur comptable).
 */
@Slf4j
@Component("FACTURE_EXCEL")
public class FactureExcel implements FactureDocument {

    @Override
    public String generer(Long idContrat, double montant) {
        log.info("[DOCUMENT - EXCEL] Génération du tableur Excel pour contrat n°{} (Montant: {} TND)", idContrat, montant);
        return "DOCUMENT_EXCEL[Contrat=" + idContrat + ", Montant=" + montant + " TND, Format=application/vnd.ms-excel]";
    }

    @Override
    public String getFormat() {
        return "EXCEL";
    }
}

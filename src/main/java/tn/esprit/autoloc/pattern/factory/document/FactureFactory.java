package tn.esprit.autoloc.pattern.factory.document;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Atelier GoF & SOLID - Partie D (Situation 1 : Factory).
 * Fabrique centralisant la création des documents de facturation (PDF, Excel).
 */
@Component
public class FactureFactory {

    private final Map<String, FactureDocument> documentMap = new HashMap<>();

    public FactureFactory(List<FactureDocument> documents) {
        if (documents != null) {
            for (FactureDocument doc : documents) {
                documentMap.put(doc.getFormat().toUpperCase(), doc);
            }
        }
    }

    /**
     * Méthode de fabrique retournant le produit approprié selon le format.
     *
     * @param format le format demandé (ex: "PDF", "EXCEL")
     * @return le FactureDocument correspondant
     * @throws IllegalArgumentException si le format n'est pas supporté
     */
    public FactureDocument creerFacture(String format) {
        if (format == null || format.isBlank()) {
            throw new IllegalArgumentException("Le format de document ne peut pas être null ou vide");
        }
        FactureDocument doc = documentMap.get(format.toUpperCase());
        if (doc == null) {
            throw new IllegalArgumentException("Format de facture non supporté : " + format + ". Formats valides : " + documentMap.keySet());
        }
        return doc;
    }
}

package tn.esprit.autoloc.pattern.lsp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Implémentation conforme à LSP pour un véhicule thermique.
 */
@Getter
@AllArgsConstructor
public class VoitureThermique implements VehiculeThermiqueLouable {

    private final Long id;
    private final String immatriculation;
    private final String modele;
    private final BigDecimal tarifJournalier;
    private int niveauCarburant; // en pourcentage (0 à 100)

    @Override
    public boolean estPretPourLocation() {
        return niveauCarburant >= 20;
    }

    @Override
    public void ravitaillerCarburant(int litres) {
        this.niveauCarburant = Math.min(100, this.niveauCarburant + litres);
    }
}

package tn.esprit.autoloc.pattern.lsp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Implémentation conforme à LSP pour un véhicule électrique.
 * Ne possède AUCUNE méthode non supportée levant UnsupportedOperationException.
 */
@Getter
@AllArgsConstructor
public class VoitureElectrique implements VehiculeElectriqueLouable {

    private final Long id;
    private final String immatriculation;
    private final String modele;
    private final BigDecimal tarifJournalier;
    private int niveauBatterie; // en pourcentage (0 à 100)

    @Override
    public boolean estPretPourLocation() {
        return niveauBatterie >= 30;
    }

    @Override
    public void rechargerBatterie(int pourcent) {
        this.niveauBatterie = Math.min(100, this.niveauBatterie + pourcent);
    }
}

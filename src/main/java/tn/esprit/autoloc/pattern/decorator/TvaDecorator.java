package tn.esprit.autoloc.pattern.decorator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Décorateur Concret : Taxe sur la Valeur Ajoutée (TVA).
 * S'applique en couche finale sur l'ensemble du sous-total décoré (base + options).
 */
public class TvaDecorator extends TarifLocationDecorator {

    private final BigDecimal tauxTva;

    public TvaDecorator(TarifLocation wrappee) {
        this(wrappee, new BigDecimal("0.19")); // 19% TVA standard en Tunisie
    }

    public TvaDecorator(TarifLocation wrappee, BigDecimal tauxTva) {
        super(wrappee);
        this.tauxTva = tauxTva;
    }

    @Override
    public BigDecimal getPrix() {
        BigDecimal sousTotal = wrappee.getPrix();
        BigDecimal montantTva = sousTotal.multiply(tauxTva);
        return sousTotal.add(montantTva).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getDescription() {
        BigDecimal sousTotal = wrappee.getPrix();
        BigDecimal montantTva = sousTotal.multiply(tauxTva).setScale(2, RoundingMode.HALF_UP);
        return wrappee.getDescription() + " + TVA " + tauxTva.multiply(BigDecimal.valueOf(100)).stripTrailingZeros().toPlainString() + "% (" + montantTva + " TND)";
    }
}

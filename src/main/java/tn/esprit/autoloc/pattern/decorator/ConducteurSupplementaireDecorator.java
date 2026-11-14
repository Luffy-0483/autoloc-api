package tn.esprit.autoloc.pattern.decorator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Décorateur Concret : Conducteur Supplémentaire.
 * Facturé par jour de location.
 */
public class ConducteurSupplementaireDecorator extends TarifLocationDecorator {

    private final BigDecimal coutParJour;
    private final int nombreJours;

    public ConducteurSupplementaireDecorator(TarifLocation wrappee, int nombreJours) {
        this(wrappee, nombreJours, new BigDecimal("15.00"));
    }

    public ConducteurSupplementaireDecorator(TarifLocation wrappee, int nombreJours, BigDecimal coutParJour) {
        super(wrappee);
        this.nombreJours = Math.max(1, nombreJours);
        this.coutParJour = coutParJour;
    }

    @Override
    public BigDecimal getPrix() {
        BigDecimal supplement = coutParJour.multiply(BigDecimal.valueOf(nombreJours));
        return wrappee.getPrix().add(supplement).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getDescription() {
        BigDecimal supplement = coutParJour.multiply(BigDecimal.valueOf(nombreJours));
        return wrappee.getDescription() + " + Conducteur Supplémentaire (" + nombreJours + "j x " + coutParJour + " = " + supplement + " TND)";
    }
}

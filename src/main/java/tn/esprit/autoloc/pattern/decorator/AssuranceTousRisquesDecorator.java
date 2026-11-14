package tn.esprit.autoloc.pattern.decorator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Décorateur Concret : Assurance Tous Risques.
 * Ajoute une couverture zéro franchise pour un montant fixe par jour.
 */
public class AssuranceTousRisquesDecorator extends TarifLocationDecorator {

    private final BigDecimal coutParJour;
    private final int nombreJours;

    public AssuranceTousRisquesDecorator(TarifLocation wrappee, int nombreJours) {
        super(wrappee);
        this.nombreJours = Math.max(1, nombreJours);
        this.coutParJour = new BigDecimal("25.00");
    }

    public AssuranceTousRisquesDecorator(TarifLocation wrappee, int nombreJours, BigDecimal coutParJour) {
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
        return wrappee.getDescription() + " + Assurance Tous Risques (" + nombreJours + "j x " + coutParJour + " = " + supplement + " TND)";
    }
}

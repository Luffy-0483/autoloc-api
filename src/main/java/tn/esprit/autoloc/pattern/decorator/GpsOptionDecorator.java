package tn.esprit.autoloc.pattern.decorator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Décorateur Concret : Option Système GPS / Navigation connectée.
 * Forfait fixe appliqué à la réservation.
 */
public class GpsOptionDecorator extends TarifLocationDecorator {

    private final BigDecimal forfaitGps;

    public GpsOptionDecorator(TarifLocation wrappee) {
        this(wrappee, new BigDecimal("20.00"));
    }

    public GpsOptionDecorator(TarifLocation wrappee, BigDecimal forfaitGps) {
        super(wrappee);
        this.forfaitGps = forfaitGps;
    }

    @Override
    public BigDecimal getPrix() {
        return wrappee.getPrix().add(forfaitGps).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + Option GPS (" + forfaitGps + " TND)";
    }
}

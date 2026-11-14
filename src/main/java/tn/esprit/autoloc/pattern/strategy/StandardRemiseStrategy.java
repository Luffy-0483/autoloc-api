package tn.esprit.autoloc.pattern.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Stratégie Standard (sans remise) : 0% de réduction.
 */
@Component("STANDARD")
public class StandardRemiseStrategy implements RemiseLocationStrategy {

    private static final BigDecimal TAUX = BigDecimal.ZERO;

    @Override
    public BigDecimal calculerMontantApresRemise(BigDecimal montantInitial) {
        if (montantInitial == null) return BigDecimal.ZERO;
        return montantInitial.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getTauxRemise() {
        return TAUX;
    }

    @Override
    public String getCodeRemise() {
        return "STANDARD";
    }
}

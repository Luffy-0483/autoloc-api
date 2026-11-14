package tn.esprit.autoloc.pattern.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Stratégie de remise Fidélité : 10% de réduction pour les clients réguliers.
 */
@Component("FIDELITE")
public class FideliteRemiseStrategy implements RemiseLocationStrategy {

    private static final BigDecimal TAUX = new BigDecimal("0.10");

    @Override
    public BigDecimal calculerMontantApresRemise(BigDecimal montantInitial) {
        if (montantInitial == null) return BigDecimal.ZERO;
        BigDecimal reduction = montantInitial.multiply(TAUX);
        return montantInitial.subtract(reduction).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getTauxRemise() {
        return TAUX;
    }

    @Override
    public String getCodeRemise() {
        return "FIDELITE";
    }
}

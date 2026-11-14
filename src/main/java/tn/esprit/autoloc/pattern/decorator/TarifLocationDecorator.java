package tn.esprit.autoloc.pattern.decorator;

import java.math.BigDecimal;

/**
 * GoF Decorator Pattern - Décorateur Abstrait (Decorator).
 * Maintient une référence vers l'objet TarifLocation encapsulé et implémente la même interface.
 */
public abstract class TarifLocationDecorator implements TarifLocation {

    protected final TarifLocation wrappee;

    protected TarifLocationDecorator(TarifLocation wrappee) {
        if (wrappee == null) {
            throw new IllegalArgumentException("Le composant tarifaire à décorer ne peut pas être null.");
        }
        this.wrappee = wrappee;
    }

    @Override
    public BigDecimal getPrix() {
        return wrappee.getPrix();
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription();
    }
}

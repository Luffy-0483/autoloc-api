package tn.esprit.autoloc.pattern.strategy;

import java.math.BigDecimal;

/**
 * GoF Strategy Pattern & SOLID OCP (Open/Closed Principle).
 * Interface commune pour tous les algorithmes de calcul de remise sur location.
 */
public interface RemiseLocationStrategy {

    /**
     * Calcule le montant final après application de la remise.
     *
     * @param montantInitial le montant brut
     * @return le montant net après remise
     */
    BigDecimal calculerMontantApresRemise(BigDecimal montantInitial);

    /**
     * Taux de réduction appliqué (ex: 0.20 pour 20%).
     */
    BigDecimal getTauxRemise();

    /**
     * Identifiant du profil ou de la stratégie de remise (ex: VIP, ETUDIANT, FIDELITE).
     */
    String getCodeRemise();
}

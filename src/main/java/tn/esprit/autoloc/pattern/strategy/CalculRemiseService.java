package tn.esprit.autoloc.pattern.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * Service de calcul de remise basé sur le Strategy Pattern et le principe OCP.
 * Les stratégies sont injectées dynamiquement par Spring dans une Map (clé = nom du Bean).
 * Ce service est totalement fermé à la modification : pour supporter une nouvelle remise
 * (ex: BLACK_FRIDAY, SOLDE_ETE), il suffit de créer une nouvelle classe @Component("CODE")
 * sans jamais modifier ce service.
 */
@Slf4j
@Service
public class CalculRemiseService {

    private final Map<String, RemiseLocationStrategy> strategies;

    public CalculRemiseService(Map<String, RemiseLocationStrategy> strategies) {
        this.strategies = strategies;
        log.info("[STRATEGY] CalculRemiseService initialisé avec {} stratégies : {}",
                strategies.size(), strategies.keySet());
    }

    /**
     * Applique la stratégie demandée au montant initial.
     * Si le code n'est pas reconnu ou null, applique la stratégie STANDARD.
     */
    public BigDecimal appliquerRemise(String codeRemise, BigDecimal montantInitial) {
        String key = (codeRemise == null || codeRemise.isBlank()) ? "STANDARD" : codeRemise.toUpperCase();
        RemiseLocationStrategy strategy = strategies.get(key);
        if (strategy == null) {
            log.warn("[STRATEGY] Aucune stratégie pour le code '{}', repli sur 'STANDARD'", codeRemise);
            strategy = strategies.getOrDefault("STANDARD", new StandardRemiseStrategy());
        }
        BigDecimal resultat = strategy.calculerMontantApresRemise(montantInitial);
        log.info("[STRATEGY] Remise '{}' appliquée : montant initial={} -> montant final={}",
                strategy.getCodeRemise(), montantInitial, resultat);
        return resultat;
    }

    public Set<String> getTypesRemiseDisponibles() {
        return strategies.keySet();
    }
}

package tn.esprit.autoloc.pattern.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Atelier GoF & SOLID - Partie C (Étape 3).
 * Service utilisant les stratégies disponibles pour calculer le prix.
 * Utilise l'injection Spring (DIP) et une collection de StrategieTarification pour retrouver la stratégie demandée.
 * Respecte OCP : fermé à la modification, ouvert à l'extension par ajout de nouvelles classes @Component.
 */
@Slf4j
@Service
public class TarificationService {

    private final Map<String, StrategieTarification> strategies = new HashMap<>();

    public TarificationService(List<StrategieTarification> strategieList) {
        if (strategieList != null) {
            for (StrategieTarification strat : strategieList) {
                String className = strat.getClass().getSimpleName().toUpperCase();
                strategies.put(className, strat);

                if (strat instanceof TarificationStandard) {
                    strategies.put("STANDARD", strat);
                } else if (strat instanceof TarificationWeekend) {
                    strategies.put("WEEKEND", strat);
                } else if (strat instanceof TarificationLongueDuree) {
                    strategies.put("LONGUE_DUREE", strat);
                    strategies.put("LONGUEDUREE", strat);
                } else if (strat instanceof TarificationEntreprise) {
                    strategies.put("ENTREPRISE", strat);
                }
            }
        }
        log.info("[STRATEGY] TarificationService initialisé avec {} alias de stratégies : {}",
                strategies.size(), strategies.keySet());
    }

    /**
     * Calcule le prix en appliquant la stratégie spécifiée.
     * Répond à la Question 4 de l'atelier :
     * "Que doit faire le service si le type de tarif demandé n'existe pas ?"
     * -> Si la stratégie n'existe pas, lève une IllegalArgumentException explicite.
     *
     * @param typePolitique code de la stratégie (ex: STANDARD, WEEKEND, LONGUE_DUREE, ENTREPRISE)
     * @param tarifJournalier tarif unitaire par jour
     * @param jours durée en jours
     * @return prix calculé
     * @throws IllegalArgumentException si la politique est inexistante ou si les paramètres sont invalides
     */
    public double calculerPrix(String typePolitique, double tarifJournalier, int jours) {
        if (tarifJournalier <= 0) {
            throw new IllegalArgumentException("Le tarif journalier doit être positif");
        }
        if (jours <= 0) {
            throw new IllegalArgumentException("Le nombre de jours doit être positif");
        }
        if (typePolitique == null || typePolitique.isBlank()) {
            throw new IllegalArgumentException("Le type de tarification est obligatoire");
        }

        StrategieTarification strategie = strategies.get(typePolitique.toUpperCase());
        if (strategie == null) {
            throw new IllegalArgumentException("Type de tarification inconnu : " + typePolitique + ". Types disponibles : " + strategies.keySet());
        }

        double resultat = strategie.calculer(tarifJournalier, jours);
        log.info("[STRATEGY] Calcul effectué par {} : {} TND/j x {} jours = {} TND",
                strategie.getClass().getSimpleName(), tarifJournalier, jours, resultat);
        return resultat;
    }

    /**
     * Variante avec repli automatique sur la politique STANDARD si la politique demandée est absente.
     */
    public double calculerPrixAvecRepli(String typePolitique, double tarifJournalier, int jours) {
        String cle = (typePolitique == null || typePolitique.isBlank()) ? "STANDARD" : typePolitique.toUpperCase();
        StrategieTarification strategie = strategies.getOrDefault(cle, strategies.get("STANDARD"));
        if (strategie == null) {
            return tarifJournalier * jours;
        }
        return strategie.calculer(tarifJournalier, jours);
    }

    public Set<String> getPolitiquesDisponibles() {
        return strategies.keySet();
    }
}

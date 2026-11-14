package tn.esprit.autoloc.pattern.factory;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.ModePaiement;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * GoF Factory Pattern - Fabrique de processeurs de paiement.
 * Centralise la création et la sélection du processeur approprié en fonction du ModePaiement.
 * Respecte OCP (l'ajout d'un nouveau processeur ne modifie pas cette fabrique grâce à l'injection Spring)
 * et DIP (dépend de l'abstraction PaiementProcessor).
 */
@Component
public class PaiementProcessorFactory {

    private final Map<ModePaiement, PaiementProcessor> processorMap = new EnumMap<>(ModePaiement.class);

    public PaiementProcessorFactory(List<PaiementProcessor> processors) {
        for (PaiementProcessor processor : processors) {
            processorMap.put(processor.getModePaiement(), processor);
        }
    }

    /**
     * Récupère le processeur correspondant au mode de paiement donné.
     *
     * @param mode le mode de paiement demandé
     * @return le PaiementProcessor adapté
     * @throws IllegalArgumentException si aucun processeur n'est configuré pour ce mode
     */
    public PaiementProcessor getProcessor(ModePaiement mode) {
        return Optional.ofNullable(processorMap.get(mode))
                .orElseThrow(() -> new IllegalArgumentException("Aucun processeur de paiement enregistré pour le mode : " + mode));
    }
}

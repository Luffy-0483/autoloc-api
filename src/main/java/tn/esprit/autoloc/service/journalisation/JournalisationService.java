package tn.esprit.autoloc.service.journalisation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Atelier GoF & SOLID - Partie B.1 : Principe de responsabilité unique (SRP).
 * Responsabilité unique : Centraliser la journalisation des opérations du système.
 */
@Slf4j
@Service
public class JournalisationService {

    /**
     * Journalise un message opérationnel.
     *
     * @param message le message à consigner
     */
    public void journaliser(String message) {
        log.info("[JOURNALISATION] {}", message);
    }
}

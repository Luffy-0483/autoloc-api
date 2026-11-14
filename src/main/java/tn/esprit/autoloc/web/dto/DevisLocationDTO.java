package tn.esprit.autoloc.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO représentant le devis détaillé issu du Decorator Pattern et de la Strategy de remise.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DevisLocationDTO {

    private Long idVehicule;
    private String immatriculation;
    private int nombreJours;
    private BigDecimal tarifJournalier;
    private BigDecimal montantBase;
    private BigDecimal montantOptionsEtTva;
    private BigDecimal montantTotalTTC;
    private String codeRemiseApplique;
    private BigDecimal montantApresRemise;
    private String detailCalcul;
}

package tn.esprit.autoloc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.service.isp.atelier.ConsultationVehicule;
import tn.esprit.autoloc.service.isp.atelier.GestionVehicule;
import tn.esprit.autoloc.service.isp.atelier.GestionVehiculeImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Atelier GoF & SOLID - Tests Partie D Situation 2 (ISP GestionVehicule)")
class GestionVehiculeIspTest {

    @Mock
    private VehiculeRepository vehiculeRepository;

    @Test
    @DisplayName("Un client peut dépendre uniquement du contrat spécialisé ConsultationVehicule")
    void testClientNeDependQueDeConsultation() {
        GestionVehicule serviceGlobal = new GestionVehiculeImpl(vehiculeRepository);

        // Injection restreinte à l'interface de consultation (ISP)
        ConsultationVehicule clientConsultation = serviceGlobal;

        Vehicule v = new Vehicule();
        v.setIdVehicule(5L);
        v.setImmatriculation("210-TUN-555");
        when(vehiculeRepository.findById(5L)).thenReturn(Optional.of(v));

        Vehicule trouve = clientConsultation.consulter(5L);

        assertNotNull(trouve);
        assertEquals("210-TUN-555", trouve.getImmatriculation());
        verify(vehiculeRepository, times(1)).findById(5L);
    }

    @Test
    @DisplayName("Les méthodes de rapport et notification de GestionVehicule s'exécutent sans impacter le contrat de consultation")
    void testMethodesSpecialisees() {
        GestionVehicule service = new GestionVehiculeImpl(vehiculeRepository);

        assertDoesNotThrow(service::exporterRapport);
        assertDoesNotThrow(service::envoyerNotification);
    }
}

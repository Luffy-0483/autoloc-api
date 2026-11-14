package tn.esprit.autoloc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;
import tn.esprit.autoloc.web.controller.VehiculeRestController;
import tn.esprit.autoloc.web.exception.GlobalExceptionHandler;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VehiculeRestControllerTest {

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private IVehiculeService vehiculeService;

    @InjectMocks
    private VehiculeRestController vehiculeRestController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vehiculeRestController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("GET /vehicule/retrieve-all-vehicules - Devrait retourner la liste des véhicules en JSON (200 OK)")
    void testRetrieveAllVehicules() throws Exception {
        Vehicule v1 = new Vehicule();
        v1.setIdVehicule(1L);
        v1.setImmatriculation("200-TN-1234");
        v1.setMarque("Renault");
        v1.setModele("Clio");
        v1.setCategorie(CategorieVehicule.CITADINE);
        v1.setTarifJournalier(new BigDecimal("120.00"));
        v1.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule v2 = new Vehicule();
        v2.setIdVehicule(2L);
        v2.setImmatriculation("201-TN-5678");
        v2.setMarque("Peugeot");
        v2.setModele("208");
        v2.setCategorie(CategorieVehicule.CITADINE);
        v2.setTarifJournalier(new BigDecimal("130.00"));
        v2.setStatut(StatutVehicule.DISPONIBLE);

        when(vehiculeService.retrieveAllVehicules()).thenReturn(Arrays.asList(v1, v2));

        mockMvc.perform(get("/vehicule/retrieve-all-vehicules"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].immatriculation").value("200-TN-1234"))
                .andExpect(jsonPath("$[1].immatriculation").value("201-TN-5678"));

        verify(vehiculeService, times(1)).retrieveAllVehicules();
    }

    @Test
    @DisplayName("GET /vehicule/retrieve-vehicule/{id} - Devrait retourner le véhicule demandé (200 OK)")
    void testRetrieveVehiculeById() throws Exception {
        Vehicule v = new Vehicule();
        v.setIdVehicule(1L);
        v.setImmatriculation("200-TN-1234");
        v.setMarque("Renault");
        v.setModele("Clio");
        v.setCategorie(CategorieVehicule.CITADINE);
        v.setTarifJournalier(new BigDecimal("120.00"));
        v.setStatut(StatutVehicule.DISPONIBLE);

        when(vehiculeService.retrieveVehicule(1L)).thenReturn(v);

        mockMvc.perform(get("/vehicule/retrieve-vehicule/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idVehicule").value(1))
                .andExpect(jsonPath("$.marque").value("Renault"))
                .andExpect(jsonPath("$.modele").value("Clio"));

        verify(vehiculeService, times(1)).retrieveVehicule(1L);
    }

    @Test
    @DisplayName("GET /vehicule/retrieve-vehicule/{id} - Devrait retourner 404 si véhicule inexistant")
    void testRetrieveVehiculeNotFound() throws Exception {
        when(vehiculeService.retrieveVehicule(999L))
                .thenThrow(new ResourceNotFoundException("Vehicule introuvable avec l'id : 999"));

        mockMvc.perform(get("/vehicule/retrieve-vehicule/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"));
    }

    @Test
    @DisplayName("POST /vehicule/add-vehicule - Devrait ajouter et renvoyer le véhicule créé")
    void testAddVehicule() throws Exception {
        Vehicule v = new Vehicule();
        v.setImmatriculation("202-TN-9999");
        v.setMarque("Volkswagen");
        v.setModele("Golf");
        v.setCategorie(CategorieVehicule.BERLINE);
        v.setTarifJournalier(new BigDecimal("180.00"));
        v.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule saved = new Vehicule();
        saved.setIdVehicule(10L);
        saved.setImmatriculation("202-TN-9999");
        saved.setMarque("Volkswagen");
        saved.setModele("Golf");
        saved.setCategorie(CategorieVehicule.BERLINE);
        saved.setTarifJournalier(new BigDecimal("180.00"));
        saved.setStatut(StatutVehicule.DISPONIBLE);

        when(vehiculeService.addVehicule(any(Vehicule.class))).thenReturn(saved);

        mockMvc.perform(post("/vehicule/add-vehicule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(v)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idVehicule").value(10))
                .andExpect(jsonPath("$.immatriculation").value("202-TN-9999"));

        verify(vehiculeService, times(1)).addVehicule(any(Vehicule.class));
    }

    @Test
    @DisplayName("DELETE /vehicule/remove-vehicule/{id} - Devrait supprimer le véhicule")
    void testRemoveVehicule() throws Exception {
        doNothing().when(vehiculeService).removeVehicule(1L);

        mockMvc.perform(delete("/vehicule/remove-vehicule/1"))
                .andExpect(status().isOk());

        verify(vehiculeService, times(1)).removeVehicule(1L);
    }
}

package tn.esprit.autoloc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tn.esprit.autoloc.pattern.factory.document.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Atelier GoF & SOLID - Tests Partie D Situation 1 (FactureFactory)")
class FactureFactoryTest {

    private FactureFactory factureFactory;

    @BeforeEach
    void setUp() {
        factureFactory = new FactureFactory(List.of(
                new FacturePdf(),
                new FactureExcel()
        ));
    }

    @Test
    @DisplayName("FactureFactory doit retourner FacturePdf pour le format 'PDF'")
    void testCreerFacturePdf() {
        FactureDocument doc = factureFactory.creerFacture("PDF");
        assertNotNull(doc);
        assertInstanceOf(FacturePdf.class, doc);
        assertEquals("PDF", doc.getFormat());

        String res = doc.generer(10L, 450.0);
        assertTrue(res.contains("DOCUMENT_PDF"));
        assertTrue(res.contains("450.0"));
    }

    @Test
    @DisplayName("FactureFactory doit retourner FactureExcel pour le format 'EXCEL'")
    void testCreerFactureExcel() {
        FactureDocument doc = factureFactory.creerFacture("EXCEL");
        assertNotNull(doc);
        assertInstanceOf(FactureExcel.class, doc);
        assertEquals("EXCEL", doc.getFormat());

        String res = doc.generer(12L, 890.0);
        assertTrue(res.contains("DOCUMENT_EXCEL"));
        assertTrue(res.contains("890.0"));
    }

    @Test
    @DisplayName("Un format non supporté doit lever une IllegalArgumentException")
    void testFormatNonSupporte() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> factureFactory.creerFacture("WORD"));
        assertTrue(ex.getMessage().contains("non supporté"));
    }
}

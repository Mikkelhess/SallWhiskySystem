package controller;

import logik.Fad;
import logik.FadType;
import logik.LeafDestillat;
import logik.WhiskyProdukt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Fad fad;

    @BeforeEach
    void setUp() {
        this.fad = new Fad(20.00, FadType.BOURBON, "Leverandør");
    }

    @Test
    void opretFad() {
        //Arrange

        double størrelse = 20.00;
        FadType fadType = FadType.BOURBON;
        //Act
        Fad actualFad = Controller.opretFad(størrelse, fadType, "Leverandør");

        //Assert
        double forventetResultat = 0;
        assertEquals(størrelse, actualFad.getFadLiter());
        assertEquals(fadType, actualFad.getFadType());
    }

    @Test
    void TC1_testOpretWhiskyProdukt() {
        //Arrange
        String whiskyType = "Single Cask";
        double liter = 7;
        int antalFlasker = 10;
        double flaskeStørrelse = 0.7;
        String vandLokation = "Randers";
        double vandLiter = 0;
        String beskrivelse = "En god whisky";
        ArrayList<LeafDestillat> destillatListe = new ArrayList<>();

        //Act
        WhiskyProdukt whiskyProdukt = Controller.opretWhiskyProdukt(whiskyType, liter, antalFlasker,
                flaskeStørrelse, vandLokation, vandLiter, beskrivelse, destillatListe);

        //Assert
        assertNotNull(whiskyProdukt);
        assertEquals(whiskyType, whiskyProdukt.getWhiskyType());
        assertEquals(liter, whiskyProdukt.getLiter());
        assertEquals(antalFlasker, whiskyProdukt.getAntalFlasker());
        assertEquals(flaskeStørrelse, whiskyProdukt.getFlaskeStørrelse());
        assertEquals(vandLokation, whiskyProdukt.getVandLokation());
        assertEquals(vandLiter, whiskyProdukt.getVandLiter());
        assertEquals(beskrivelse, whiskyProdukt.getBeskrivelse());
        assertEquals(destillatListe, whiskyProdukt.getDestillatListe());
    }

    @Test
    void TC2_testOpretWhiskyProdukt_medUgyldigData() {
        // Arrange
        String whiskyType = "Single Cask";
        double liter = -7;
        int antalFlasker = 10;
        double flaskeStørrelse = 0.7;
        String vandLokation = "Randers";
        double vandLiter = 0;
        String beskrivelse = "En god whisky";
        ArrayList<LeafDestillat> destillatListe = new ArrayList<>();

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Controller.opretWhiskyProdukt(whiskyType, liter, antalFlasker, flaskeStørrelse, vandLokation, vandLiter, beskrivelse, destillatListe);
        });

        assertEquals("Ugyldige data. Check liter, antal flasker, flaskestørrelse og vandliter.", exception.getMessage());
    }

    @Test
    void TC3_testOpretWhiskyProdukt_whiskyTypeErNull() {
        // Arrange
        String whiskyType = null;
        double liter = 7;
        int antalFlasker = 10;
        double flaskeStørrelse = 0.7;
        String vandLokation = "Randers";
        double vandLiter = 0;
        String beskrivelse = "En god whisky";
        ArrayList<LeafDestillat> destillatListe = new ArrayList<>();

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Controller.opretWhiskyProdukt(whiskyType, liter, antalFlasker, flaskeStørrelse, vandLokation, vandLiter, beskrivelse, destillatListe);
        });
        assertEquals("Whiskytype må ikke være tom eller null", exception.getMessage());
    }

    @Test
    void TC4_testOpretWhiskyProdukt_whiskyTypeErTom() {
        // Arrange
        String whiskyType = "";
        double liter = 7;
        int antalFlasker = 10;
        double flaskeStørrelse = 0.7;
        String vandLokation = "Randers";
        double vandLiter = 0;
        String beskrivelse = "En god whisky";
        ArrayList<LeafDestillat> destillatListe = new ArrayList<>();

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Controller.opretWhiskyProdukt(whiskyType, liter, antalFlasker, flaskeStørrelse, vandLokation, vandLiter, beskrivelse, destillatListe);
        });
        assertEquals("Whiskytype må ikke være tom eller null", exception.getMessage());
    }
}


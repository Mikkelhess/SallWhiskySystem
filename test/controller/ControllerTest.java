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
        Fad actualFad = Controller.opretFad(størrelse,fadType, "Leverandør");

        //Assert
        double forventetResultat = 0;
        assertEquals(størrelse,actualFad.getFadLiter());
        assertEquals(fadType,actualFad.getFadType());
    }

    @Test
    void testOpretWhiskyProdukt() {
        String whiskyType = "Single Cask";
        double liter = 7;
        int antalFlasker = 10;
        double flaskeStørrelse = 0.7;
        String vandLokation = "Randers";
        double vandLiter = 0;
        String beskrivelse = "En god whisky";
        ArrayList<LeafDestillat> destillatListe = new ArrayList<>();
        WhiskyProdukt whiskyProdukt = Controller.opretWhiskyProdukt(whiskyType, liter, antalFlasker, flaskeStørrelse, vandLokation, vandLiter, beskrivelse, destillatListe);
        assertNotNull(whiskyProdukt);
        assertEquals(whiskyType, whiskyProdukt.getWhiskyType());
        assertEquals(liter, whiskyProdukt.getLiter(), 7);
        assertEquals(antalFlasker, whiskyProdukt.getAntalFlasker());
        assertEquals(flaskeStørrelse, whiskyProdukt.getFlaskeStørrelse(), 0.7);
        assertEquals(vandLokation, whiskyProdukt.getVandLokation());
        assertEquals(vandLiter, whiskyProdukt.getVandLiter(), 0);
        assertEquals(beskrivelse, whiskyProdukt.getBeskrivelse());
        assertEquals(destillatListe, whiskyProdukt.getDestillatListe());
    }
}


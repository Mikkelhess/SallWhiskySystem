package logik;

import controller.Controller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HyldeTest {

    private Hylde hylde;
    private Fad fad;

    @Test
    void addFadTilHylde() {
        //Arrange
        hylde = new Hylde();
        fad = new Fad(20.00,FadType.BOURBON, "Sall");

        //Act
        hylde.addFadTilHylde(fad);

        //Assert
        assertTrue(hylde.getFadPåHyldeMap().containsValue(fad));
    }

    @Test
    void removeFadFraHylde() {
        Hylde hylde = new Hylde();
        Fad fad1 = new Fad(20.00,FadType.BOURBON, "Sall");
        Fad fad2 = new Fad(10.00,FadType.RØDVIN, "Sall");
        hylde.addFadTilHylde(fad1);
        hylde.addFadTilHylde(fad2);

        //Act
        hylde.removeFadFraHylde(fad1.getFadId());

        //Assert
        assertNull(hylde.getFadPåHyldeMap().get(fad1.getFadId())); // Tjekker om det første fad er fjernet
        assertNotNull(hylde.getFadPåHyldeMap().get(fad2.getFadId())); // Tjekker om det andet fad stadig er på hylden
    }
}
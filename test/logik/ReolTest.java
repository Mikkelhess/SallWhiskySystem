package logik;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReolTest {
    private Reol reol;
    private Hylde hylde;

    @Test
    void createHylde() {
        //Arrange
        Reol reol = new Reol();

        //Act
        Hylde hylde = reol.createHylde();

        //Assert
        assertNotNull(hylde);
        assertEquals(1, reol.getHyldeMap().size()); // Tjek om der er tilføjet en ny hylde til reolen
        assertTrue(reol.getHyldeMap().containsKey(hylde.getHyldeId())); // Tjek om hylde findes i reolens hyldeMap
    }


    @Test
    void addHylde() {
        //Arrange
        reol = new Reol();
        hylde = new Hylde();

        //Act
        reol.addHylde(hylde);

        //Assert
        assertTrue(reol.getHyldeMap().containsValue(hylde));
    }
}
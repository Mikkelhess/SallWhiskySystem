package logik;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {
    private Lager lager;
    private Reol reol;

    @org.junit.jupiter.api.Test
    void createReol() {
        //Arrange
        lager = new Lager();

        //Act
        Reol reol = lager.createReol();

        //Assert
        assertNotNull(reol);
        assertEquals(1, lager.getReolMap().size()); // Vi tjekker, om der er tilføjet en ny reol til lager
        assertTrue(lager.getReolMap().containsKey(reol.getReolId())); // Tjekker om reol findes i lagers hyldeMap
    }


    @org.junit.jupiter.api.Test
    void addReol() {
        //Arrange
        lager = new Lager();
        reol = new Reol();

        //Act
        lager.addReol(reol);

        //Assert
        lager.getReolMap().containsValue(reol);
    }

    @org.junit.jupiter.api.Test
    void removeReol() {
        //Arrange
        Lager lager = new Lager();
        Reol reol = lager.createReol();
        Hylde hylde1 = reol.createHylde();
        Hylde hylde2 = reol.createHylde();

        //Act
        lager.removeReol(reol.getReolId());

        //Assert
        assertNull(lager.getReol(reol.getReolId())); // Vi tjekker om reolen er blevet fjernet fra lageret
        assertEquals(0, lager.getReolMap().size()); // Tjekker om lageret er tomt
        assertNull(reol.getHylde(hylde1.getHyldeId())); // Tjekker om den første hylde er blevet fjernet fra reolen
        assertNull(reol.getHylde(hylde2.getHyldeId())); // Tjekker om den anden hylde er blevet fjernet fra reolen
    }


    @Test
    void getReolMap() {
        // Arrange
        Lager lager = new Lager();
        Reol reol1 = lager.createReol();
        Reol reol2 = lager.createReol();
        Reol reol3 = lager.createReol();

        // Act
        HashMap<Integer, Reol> reolMap = lager.getReolMap();

        // Assert
        assertEquals(3, reolMap.size()); // det forventes, at der er tilføjet 3 reoler til lageret
        assertTrue(reolMap.containsValue(reol1)); // det forventes, at reol1 er en af de tilføjede reoler
        assertTrue(reolMap.containsValue(reol2)); // det forventes, at reol2 er en af de tilføjede reoler
        assertTrue(reolMap.containsValue(reol3)); // det forventes, at reol3 er en af de tilføjede reoler
    }

    @Test
    void getReol() {
        // Arrange
        Lager lager = new Lager();
        Reol reol1 = lager.createReol();
        Reol reol2 = lager.createReol();

        // Act & Assert
        assertEquals(reol1, lager.getReol(reol1.getReolId())); // det forventes, at den korrekte reol hentes ved brug af dens id
        assertEquals(reol2, lager.getReol(reol2.getReolId())); // det forventes, at den korrekte reol hentes ved brug af dens id
        assertNull(lager.getReol(999)); // Null returneres, når der forsøges at hente en reol med et ikke-eksisterende id
    }

}

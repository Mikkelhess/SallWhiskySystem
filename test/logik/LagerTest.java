package logik;

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
        assertEquals(1, lager.getReolMap().size()); // Tjek om der er tilføjet en ny reol til lager
        assertTrue(lager.getReolMap().containsKey(reol.getReolId())); // Tjek om reol findes i lagers hyldeMap
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
        assertNull(lager.getReol(reol.getReolId())); // Tjek om reolen er blevet fjernet fra lageret
        assertEquals(0, lager.getReolMap().size()); // Tjek om lageret er tomt
        assertNull(reol.getHylde(hylde1.getHyldeId())); // Tjek om den første hylde er blevet fjernet fra reolen
        assertNull(reol.getHylde(hylde2.getHyldeId())); // Tjek om den anden hylde er blevet fjernet fra reolen
    }
}
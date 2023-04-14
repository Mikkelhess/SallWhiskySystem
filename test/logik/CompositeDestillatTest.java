package logik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeDestillatTest {
    @Test
    public void TC1_testCreateLeaf() {
        // Arrange
        CompositeDestillat destillat = new CompositeDestillat(100);
        double liter = 10.0;

        // Act
        LeafDestillat leafDestillat = destillat.createLeaf(liter);

        // Assert
        assertNotNull(leafDestillat);
        assertEquals(liter, leafDestillat.getLiter());
        assertEquals("1.1", leafDestillat.getNewMakeNummer());
        assertEquals(destillat.getAlkoholProcent(), leafDestillat.getAlkoholProcent());
        assertEquals(1, destillat.getComponents().size());
        assertEquals(liter, destillat.getBrugteLiter());
    }

    @Test
    void TC2_createLeaf_tomInput() {
        // Arrange
        CompositeDestillat destillat = new CompositeDestillat(100);
        double liter = 0;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            destillat.createLeaf(liter);
        });
        assertEquals("Ugyldig data for 'liter' parameter: " + liter, exception.getMessage());
        assertEquals(0, destillat.getComponents().size());
        assertEquals(0.0, destillat.getBrugteLiter());
    }

    @Test
    void createLeaf_negativInput() {
        // Arrange
        CompositeDestillat destillat = new CompositeDestillat(100);
        double liter = -5.0;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            destillat.createLeaf(liter);
        });
        assertEquals("Ugyldig data for 'liter' parameter: " + liter, exception.getMessage());
        assertEquals(0, destillat.getComponents().size());
        assertEquals(0.0, destillat.getBrugteLiter());
    }

}



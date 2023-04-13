package logik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeDestillatTest {
    @Test
    public void testCreateLeaf() {
        CompositeDestillat destillat = new CompositeDestillat(100);
        LeafDestillat leafDestillat = destillat.createLeaf(10.0);

        assertNotNull(leafDestillat);
        assertEquals(10.0, leafDestillat.getLiter());
        assertEquals("1.1", leafDestillat.getNewMakeNummer());
        assertEquals(destillat.getAlkoholProcent(), leafDestillat.getAlkoholProcent());
        assertEquals(1, destillat.getComponents().size());
        assertEquals(10.0, destillat.getBrugteLiter());
    }
}


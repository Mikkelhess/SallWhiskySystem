package logik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DestilleringTest {

    private Destillering destillering;

    @BeforeEach
    public void setUp() {
        destillering = new Destillering("John Doe", LocalDate.now(), LocalDate.now().plusDays(1),
                "Malt batch", "Kornsort", 100.0,
                "Ryge materiale", "Kommentar");
    }

    @Test
    public void testCreateDestillat() {
        CompositeDestillat compositeDestillat = destillering.createDestillat(10.0, 40.0);
        assertNotNull(compositeDestillat);
        assertEquals(10.0, compositeDestillat.getTotalLiter());
        assertEquals(40.0, compositeDestillat.getAlkoholProcent());
        assertEquals(90.0, destillering.getLiter());
    }

    @Test
    public void testAddDestillat() {
        CompositeDestillat compositeDestillat = new CompositeDestillat(40.0);
        destillering.addDestillat("Make-001", compositeDestillat);
        assertEquals(compositeDestillat, destillering.getDestillatMap().get("Make-001"));
    }

    @Test
    public void testRemoveDestillat() {
        CompositeDestillat compositeDestillat = new CompositeDestillat(40.0);
        destillering.addDestillat("Make-001", compositeDestillat);
        destillering.removeDestillat("Make-001");
        assertEquals(null, destillering.getDestillatMap().get("Make-001"));
    }

    @Test
    public void testUdregnBrugteLiter() {
        CompositeDestillat compositeDestillat1 = new CompositeDestillat(40.0);
        compositeDestillat1.setTotalLiter(10.0);
        destillering.addDestillat("Make-001", compositeDestillat1);
        CompositeDestillat compositeDestillat2 = new CompositeDestillat(45.0);
        compositeDestillat2.setTotalLiter(20.0);
        destillering.addDestillat("Make-002", compositeDestillat2);
        assertEquals(70.0, destillering.udregnBrugteLiter());
    }

}
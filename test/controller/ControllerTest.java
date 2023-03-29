package controller;

import logik.Fad;
import logik.FadType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Fad fad;

    @BeforeEach
    void setUp() {
        this.fad = new Fad(20.00, FadType.BOURBON);
    }

    @Test
    void opretFad() {
        //Arrange

        double størrelse = 20.00;
        FadType fadType = FadType.BOURBON;
        //Act
        Fad actualFad = Controller.opretFad(størrelse,fadType);

        //Assert
        double forventetResultat = 0;
        assertEquals(størrelse,actualFad.getFadLiter());
        assertEquals(fadType,actualFad.getFadType());
    }
}

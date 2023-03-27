package controller;

import logik.Fad;
import logik.FadType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Fad fad;
    @BeforeEach
    void setUp(){
        this.fad = new Fad(20.00, FadType.BOURBON);
    }
    @Test
    void opretFad() {
            //Arrange
            Controller controller = Controller.getTestController();

            //Act
            double faktiskResultat = controller

            //Assert
            double forventetResultat = 0;
            assertEquals(forventetResultat,faktiskResultat);
        }
    }
}
package controller;

import logik.Lager;
import storage.Storage;

public class Controller {

    private Storage storage;
    private static Controller controller;

    private Controller(){storage = new Storage();}

    public static Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public static Controller getTestController() {return new Controller();}

    public Lager opretLager() {
        Lager lager = new Lager();

    }
}

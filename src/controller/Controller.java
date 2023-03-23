package controller;

import logik.Hylde;
import logik.Lager;
import logik.Reol;
import storage.Storage;


public class Controller {

    private static Controller controller;

    public static Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public static Controller getTestController() {return new Controller();}

    public Lager opretLager() {
        Lager lager = new Lager();
        Storage.addLager(lager);
        return lager;
    }

    public Reol opretReol() {
        Reol reol = new Reol();
        Storage.addLager(reol);
        return reol;
    }

    public Hylde opretHylde() {
        Hylde hylde = new Hylde();
        Storage.addLager(hylde);
        return hylde;
    }



}
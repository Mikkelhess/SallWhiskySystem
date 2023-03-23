package controller;

import logik.*;
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

    public Reol opretReol(Lager lager) {
        Reol reol = lager.createReol();
        Storage.addReol(reol);
        return reol;
    }

    public Hylde opretHylde(Reol reol) {
        Hylde hylde = reol.createHylde();
        Storage.addHylde(hylde);
        return hylde;
    }

    public Fad opretFad(double størrelse, FadType fadType) {
        Fad fad = new Fad(størrelse, fadType);
        Storage.addFad(fad);
        return fad;
    }

    public void addFad(Hylde hylde, Fad fad) {
        hylde.addFad(fad);
    }



}
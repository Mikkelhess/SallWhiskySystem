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

    /**
     * Opretter et nyt lager
     * @return lager
     */
    public Lager opretLager() {
        Lager lager = new Lager();
        Storage.addLager(lager);
        return lager;
    }

    /**
     * Opretter en ny reol
     * @param lager der hvor reolen skal oprettes
     * @return reol
     */
    public Reol opretReol(Lager lager) {
        if (lager == null) {throw new NullPointerException("Angiv et lager.");}

        Reol reol = lager.createReol();
        Storage.addReol(reol);
        return reol;
    }

    /**
     * Opretter en ny hylde
     * @param reol der hvor hylden skal oprettes
     * @return hylde
     */
    public Hylde opretHylde(Reol reol) {
        if (reol == null) {throw new NullPointerException("Angiv en reol.");}

        Hylde hylde = reol.createHylde();
        Storage.addHylde(hylde);
        return hylde;
    }

    /**
     * Opretter et fad
     * @param størrelse på fad i liter
     * @param fadType hvad der har lagret i fadet inden, eks. sherry
     * @return fad
     */
    public Fad opretFad(double størrelse, FadType fadType) {
        if (størrelse <= 0 || fadType == null) {throw new NullPointerException("Angiv korrekte oplysninger.");}

        Fad fad = new Fad(størrelse, fadType);
        Storage.addFad(fad);
        return fad;
    }

    /**
     * Tilføjer et fad til en hylde
     * @param hylde hvor fadet skal tilføjes
     * @param fad der tilføjes
     */
    public void addFad(Hylde hylde, Fad fad) {
        hylde.addFad(fad);
        // TODO
    }

    public static Lager getLager(int lagerId) {
        return Storage.getLager(lagerId);
    }

    public static void removeLager(int lagerId) {
        Storage.removeLager(lagerId);
    }

    public static Reol getReol(int reolId) {
        return Storage.getReol(reolId);
    }

    public static void removeReol(int reolId) {
        Storage.removeReol(reolId);
    }

    public static Hylde getHylde(int hyldeId) {
        return Storage.getHylde(hyldeId);
    }

    public static void removeHylde(int hyldeId) {
        Storage.removeHylde(hyldeId);
    }

    public static Fad getFad(int fadId) {
        return Storage.getFad(fadId);
    }

    public static void removeFad(int fadId) {
        Storage.removeFad(fadId);
    }




}
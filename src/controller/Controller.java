package controller;

import logik.*;
import storage.Storage;

import java.util.HashMap;


public class Controller {

    private Storage storage;
    private Controller controller;

    private Controller() {storage = new Storage();}

    public Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public Controller getTestController() {return new Controller();}

    /**
     * Opretter et nyt lager
     * @return lager
     */
    public Lager opretLager() {
        Lager lager = new Lager();
        storage.addLager(lager);
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
        storage.addReol(reol);
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
        storage.addHylde(hylde);
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
        storage.addFad(fad);
        return fad;
    }

    /**
     * Tilføjer et fad til en hylde
     * @param hylde hvor fadet skal tilføjes
     * @param fad der tilføjes
     */
    public void addFadtilHylde(Hylde hylde, Fad fad) {
        if (hylde == null || fad == null) {throw new NullPointerException("Angiv korrekt information.");}

        storage.addFadTilHylde(fad, hylde);
    }

    public Lager getLager(int lagerId) {
        return storage.getLager(lagerId);
    }

    public void removeLager(int lagerId) {
        storage.removeLager(lagerId);
    }

    public Reol getReol(int reolId) {
        return storage.getReol(reolId);
    }

    public void removeReol(int lagerId, int reolId) {
        Lager lager = getLager(lagerId);
        lager.removeReol(reolId);
        storage.removeReol(reolId);
    }

    public Hylde getHylde(int hyldeId) {
        return storage.getHylde(hyldeId);
    }

    public void removeHylde(int reolId, int hyldeId) {
        Reol reol = getReol(reolId);
        reol.removeHylde(hyldeId);
        storage.removeHylde(hyldeId);
    }

    public Fad getFad(int fadId) {
        return storage.getFad(fadId);
    }

    public void removeFad(int hyldeId, int fadId) {
        Hylde hylde = getHylde(hyldeId);
        hylde.removeFad(fadId);
        storage.removeFad(fadId);
    }

    public HashMap<Integer, Lager> getLagerMap() {
        return storage.getLagerMap();
    }

    public HashMap<Integer, Reol> getReolMap() {
        return storage.getReolMap();
    }

    public HashMap<Integer, Hylde> getHyldeMap() {
        return storage.getHyldeMap();
    }

    public HashMap<Integer, Fad> getFadMap() {
        return storage.getFadMap();
    }


//--------------------------------------------------------------------------------------

    public void initStorage() {
        Lager lager = opretLager();
        Lager lager2 = opretLager();

        Reol reol = opretReol(lager);
        Reol reol2 = opretReol(lager2);

        Hylde hylde = opretHylde(reol);
        Hylde hylde2 = opretHylde(reol);
        Hylde hylde3 = opretHylde(reol);
        Hylde hylde4 = opretHylde(reol2);
        Hylde hylde5 = opretHylde(reol2);
        Hylde hylde6 = opretHylde(reol2);

        Fad fad1 = opretFad(50, FadType.SHERRY);
        Fad fad2 = opretFad(30, FadType.PORTVIN);
        Fad fad3 = opretFad(100, FadType.BOURBON);
        Fad fad4 = opretFad(20, FadType.COGNAC);
        Fad fad5 = opretFad(40, FadType.RØDVIN);
        Fad fad6 = opretFad(57, FadType.COGNAC);
        Fad fad7 = opretFad(80, FadType.RØDVIN);
        Fad fad8 = opretFad(104, FadType.BOURBON);
        Fad fad9 = opretFad(44, FadType.COGNAC);
        Fad fad10 = opretFad(47, FadType.RØDVIN);
        Fad fad11 = opretFad(77, FadType.SHERRY);
        Fad fad12 = opretFad(22, FadType.RØDVIN);
        Fad fad13 = opretFad(99, FadType.PORTVIN);
        Fad fad14 = opretFad(88, FadType.BOURBON);
        Fad fad15 = opretFad(64, FadType.COGNAC);
        Fad fad16 = opretFad(75, FadType.RØDVIN);

        addFadtilHylde(hylde, fad1);
        addFadtilHylde(hylde, fad2);
        addFadtilHylde(hylde2, fad3);
        addFadtilHylde(hylde3, fad4);
        addFadtilHylde(hylde3, fad5);
        addFadtilHylde(hylde4, fad6);
        addFadtilHylde(hylde4, fad7);
        addFadtilHylde(hylde5, fad8);
        addFadtilHylde(hylde6, fad9);
        addFadtilHylde(hylde6, fad10);

    }

}
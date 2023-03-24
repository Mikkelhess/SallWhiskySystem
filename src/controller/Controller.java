package controller;

import logik.*;
import storage.Storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Controller {

    /**
     * Opretter et nyt lager
     * @return lager
     */
    public static Lager opretLager() {
        Lager lager = new Lager();
        Storage.addLager(lager);
        return lager;
    }

    /**
     * Opretter et fad
     * @param størrelse på fad i liter
     * @param fadType hvad der har lagret i fadet inden, eks. sherry
     * @return fad
     */
    public static Fad opretFad(double størrelse, FadType fadType) {
        if (størrelse <= 0 || fadType == null) {throw new NullPointerException("Angiv korrekte oplysninger.");}

        Fad fad = new Fad(størrelse, fadType);
        Storage.addFad(fad);
        return fad;
    }

    /**
     * Opretter en ny reol
     * @param lager der hvor reolen skal oprettes
     * @return reol
     */
    public static Reol opretReol(Lager lager) {
        if (lager == null) {throw new NullPointerException("Angiv et lager.");}
        Reol reol = lager.createReol();
        return reol;
    }

    /**
     * Opretter en ny hylde
     * @param reol der hvor hylden skal oprettes
     * @return hylde
     */
    public static Hylde opretHylde(Reol reol) {
        if (reol == null) {throw new NullPointerException("Angiv en reol.");}
        Hylde hylde = reol.createHylde();
        return hylde;
    }



    /**
     * Tilføjer et fad til en hylde
     * @param hylde hvor fadet skal tilføjes
     * @param fad der tilføjes
     */
    public static void addFadtilHylde(Hylde hylde, Fad fad) {
        if (hylde == null || fad == null) {throw new NullPointerException("Angiv korrekt information.");}

        hylde.addFadTilHylde(fad);
    }

    public static Lager getLager(int lagerId) {
        return Storage.getLagerMap().get(lagerId);
    }
    public static void removeLager(Lager lager) {
        Storage.removeLager(lager);
    }

    // Mangler begrænsninger
    public static Fad getFad(int fadId) {
        return getFadMap().get(fadId);
    }

    public static void removeFad(int lagerId, int reolId, int hyldeId, int fadId) {
        Hylde hylde = getHylde(lagerId, reolId, hyldeId);
        if (hylde != null) {
            hylde.removeFadFraHylde(fadId);
        }
    }

    public static Reol getReol(int lagerId, int reolId) {
        Lager lager = getLager(lagerId);
        return lager.getReol(reolId);
    }

    // Mangler begrænsninger
    public static void removeReol(int lagerId, int reolId) {
        Lager lager = getLager(lagerId);
        if (lager != null) {
            lager.removeReol(reolId);
        }
    }

    public static Hylde getHylde(int lagerId, int reolId, int hyldeId) {
        Reol reol = getReol(lagerId, reolId);
        return reol.getHylde(hyldeId);
    }

    // Mangler begrænsninger
    public static void removeHylde(int lagerId, int reolId, int hyldeId) {
        Reol reol = getReol(lagerId, reolId);
        reol.removeHylde(hyldeId);
    }

    // Mangler begrænsninger
    public static void removeFadFraHylde(int lagerId, int reolId, int hyldeId, int fadId) {
        Hylde hylde = getHylde(lagerId, reolId, hyldeId);
        hylde.removeFadFraHylde(fadId);
    }


    public static HashMap<Integer,Lager> getLagerMap() {
        return Storage.getLagerMap();
    }

    public static HashMap<Integer,Fad> getFadMap() {
        return Storage.getFadMap();
    }

    public static List<Fad> getFadUdenHyldeMap() {
        ArrayList<Fad> fadUdenHyldeListe = new ArrayList<>();

        return null;
    }

    public static HashMap<Integer,Reol> getReolMap(int lagerId) {
        Lager lager = getLager(lagerId);
        return lager.getReolMap();
    }

    public static HashMap<Integer,Hylde> getHyldeMap(int lagerId, int reolId) {
        Reol reol = getReol(lagerId, reolId);
        return reol.getHyldeMap();
    }

    public static HashMap<Integer,Fad> getFadPåHyldeMap(int lagerId, int reolId, int hyldeId) {
        Hylde hylde = getHylde(lagerId, reolId, hyldeId);
        return hylde.getFadPåHyldeMap();
    }



//--------------------------------------------------------------------------------------

    public static void initStorage() {
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
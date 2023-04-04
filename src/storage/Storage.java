package storage;

import logik.*;

import java.util.HashMap;


public class Storage {

    private static HashMap<Integer, Lager> lagerMap = new HashMap<>();
    private static HashMap<Integer, Fad> fadMap = new HashMap<>();
    private static HashMap<Integer, Destillering> destilleringMap = new HashMap<>();
    private static HashMap<Integer, Destillat> destillatMap = new HashMap<>();

    public static Lager addLager(Lager lager) {
        lagerMap.put(lager.getLagerId(), lager);
        return lager;
    }

    public static void addFad(Fad fad) {
        fadMap.put(fad.getFadId(), fad);
    }


    public static void addDestillering(Destillering destillering) {
        destilleringMap.put(destillering.getDestilleringId(), destillering);

    }

    public static void addDestillat(Destillat destillat) {
        destillatMap.put(destillat.getNewMakeNummer(), destillat);
    }

    public static void removeDestillat(Destillat destillat){
        destillatMap.remove(destillat.getNewMakeNummer());
    }

    public static void removeLager(Lager lager) {
        lagerMap.remove(lager.getLagerId());
    }

    public static void removeFad(Fad fad) {
        fadMap.remove(fad);
    }


    public static HashMap<Integer, Lager> getLagerMap() {
        return lagerMap;
    }

    public static HashMap<Integer, Fad> getFadMap() {
        return fadMap;
    }

    public static HashMap<Integer, Destillering> getDestilleringMap() {
        return destilleringMap;
    }

    public static HashMap<Integer, Destillat> getDestillatMap() {
        return destillatMap;
    }


}



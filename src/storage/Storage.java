package storage;

import logik.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Storage {
    private static Storage instance = null;

    private static List<LeafDestillat> brugteDestillater = new ArrayList<>();
    private HashMap<Integer, Lager> lagerMap;
    private HashMap<Integer, Fad> fadMap;
    private HashMap<Integer, Destillering> destilleringMap;
    private HashMap<String, CompositeDestillat> destillatMap;
    private HashMap<Integer, WhiskyProdukt> whiskyMap;

    private Storage() {
        lagerMap = new HashMap<>();
        fadMap = new HashMap<>();
        destilleringMap = new HashMap<>();
        destillatMap = new HashMap<>();
        whiskyMap = new HashMap<>();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public LeafDestillat addBrugtDestillat(LeafDestillat leafDestillat) {
        brugteDestillater.add(leafDestillat);
        return leafDestillat;
    }

    public Lager addLager(Lager lager) {
        lagerMap.put(lager.getLagerId(), lager);
        return lager;
    }

    public WhiskyProdukt addWhisky(WhiskyProdukt whiskyProdukt) {
        whiskyMap.put(whiskyProdukt.getWhiskyId(), whiskyProdukt);
        return whiskyProdukt;
    }

    public void removeWhisky(WhiskyProdukt whiskyProdukt) {
        whiskyMap.remove(whiskyProdukt.getWhiskyId());
    }


    public void addFad(Fad fad) {
        fadMap.put(fad.getFadId(), fad);
    }


    public void addDestillering(Destillering destillering) {
        destilleringMap.put(destillering.getDestilleringId(), destillering);

    }

    public void addDestillat(CompositeDestillat compositeDestillat) {
        destillatMap.put(compositeDestillat.getNewMakeNummer(), compositeDestillat);
    }

    public void removeDestillat(CompositeDestillat compositeDestillat) {
        destillatMap.remove(compositeDestillat.getNewMakeNummer());
    }

    public void removeLager(Lager lager) {
        lagerMap.remove(lager.getLagerId());
    }

    public void removeFad(Fad fad) {
        fadMap.remove(fad);
    }

    public HashMap<Integer, Lager> getLagerMap() {
        return lagerMap;
    }

    public HashMap<Integer, Fad> getFadMap() {
        return fadMap;
    }

    public HashMap<Integer, Destillering> getDestilleringMap() {
        return destilleringMap;
    }

    public HashMap<String, CompositeDestillat> getDestillatMap() {
        return destillatMap;
    }

    public HashMap<Integer, WhiskyProdukt> getWhiskyMap() {
        return whiskyMap;
    }

    public List<LeafDestillat> getBrugteDestillater() {
        return new ArrayList<>(brugteDestillater);
    }
}

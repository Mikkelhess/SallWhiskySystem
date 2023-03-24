package storage;

import logik.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {
    private static final HashMap<Integer, Lager> lagerMap = new HashMap<>();

    private static final HashMap<Integer, Fad> fadMap = new HashMap<>();

    public void addLager(Lager lager) {
        lagerMap.put(lager.getLagerId(), lager);
    }

    public void addFad(Fad fad) {
        fadMap.put(fad.getFadId(), fad);
    }

    public Lager getLager(int lagerId) {
        return lagerMap.get(lagerId);
    }

    public void removeLager(int lagerId) {
        lagerMap.remove(lagerId);
    }

    public Fad getFad(int fadId) {
        return fadMap.get(fadId);
    }

    public void removeFad(int fadId) {
        fadMap.remove(fadId);
    }

    public List<Lager> getLagerList() {
        return new ArrayList<Lager>(lagerMap.values());
    }

    public List<Fad> getFadList() {
        return new ArrayList<Fad>(fadMap.values());
    }

}


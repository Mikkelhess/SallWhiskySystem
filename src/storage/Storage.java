package storage;

import logik.*;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {

    private static HashMap<Integer,Lager> lagerMap = new HashMap<>();
    private static HashMap<Integer,Fad> fadMap = new HashMap<>();

    public static Lager addLager(Lager lager) {
        lagerMap.put(lager.getLagerId(),lager);
        return lager;
    }

    public static void addFad(Fad fad) {
        fadMap.put(fad.getFadId(),fad);
    }

    public static void removeLager(Lager lager) {
        lagerMap.remove(lager.getLagerId());
    }

    public static void removeFad(int fadId) {
        fadMap.remove(fadId);
    }

    public static void removeReol(int reolId) {
        fadMap.remove(reolId);
    }

    public static HashMap<Integer,Lager> getLagerMap() {
        return lagerMap;
    }

    public static HashMap<Integer,Fad> getFadMap() {
        return fadMap;
    }


}


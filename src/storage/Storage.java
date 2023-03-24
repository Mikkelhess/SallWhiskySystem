package storage;

import logik.*;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {

    private static HashMap<Integer,Lager> lagerMap = new HashMap<>();
    private static HashMap<Integer,Fad> fadMap = new HashMap<>();
    private static HashMap<Integer,Reol> reolMap = new HashMap<>();
    private static HashMap<Integer,Hylde> hyldeMap = new HashMap<>();



    public static Lager addLager(Lager lager) {
        lagerMap.put(lager.getLagerId(),lager);
        return lager;
    }

    public static void addFad(Fad fad) {
        fadMap.put(fad.getFadId(),fad);
    }

    public static void addReol(Reol reol) {
        reolMap.put(reol.getReolId(),reol);
    }

    public static void addHylde(Hylde hylde) {
        hyldeMap.put(hylde.getHyldeId(), hylde);
    }

    public static void removeLager(Lager lager) {
        lagerMap.remove(lager.getLagerId());
    }

    public static void removeFad(Fad fad) {
        fadMap.remove(fad);
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

    public static HashMap<Integer,Reol> getReolMap() {
        return reolMap;
    }

    public static HashMap<Integer,Hylde> getHyldeMap() {
        return hyldeMap;
    }



}


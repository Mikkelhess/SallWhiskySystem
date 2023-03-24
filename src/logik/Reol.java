package logik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Reol {

    private static int idCounter = 1;
    private final int reolId;
    private HashMap<Integer, Hylde> hyldeMap = new HashMap<>();

    public Reol() {
        this.reolId = idCounter;
        idCounter++;
    }

    public Hylde createHylde() {
        Hylde hylde = new Hylde();
        addHylde(hylde);
        return hylde;
    }

    public void addHylde(Hylde hylde) {
        hyldeMap.put(hylde.getHyldeId(), hylde);
    }

    public void removeHylde(int hyldeId) {
        hyldeMap.remove(hyldeId);
    }

    public Hylde getHylde(int hyldeId) {
    return hyldeMap.get(hyldeId);
    }

    public HashMap<Integer, Hylde> getHyldeMap() {
        return hyldeMap;
    }

    public int getReolId() {
        return reolId;
    }

    @Override
    public String toString() {
        return "Reol: " + reolId;
    }


}

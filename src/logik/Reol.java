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

        // TODO
        // Mangler en for-each som fjerner fad fra hylden, hvis der altaå er nogen
    }

    public int getReolId() {
        return reolId;
    }

    public List<Hylde> getHyldeList() {
        return new ArrayList<>(hyldeMap.values());
    }

    public Hylde getHylde(int hyldeId) {
        for (Hylde hylde : getHyldeList()) {
            if (hyldeId == hylde.getHyldeId()) {
                return hylde;
            }
        }
        return null;
    }



}

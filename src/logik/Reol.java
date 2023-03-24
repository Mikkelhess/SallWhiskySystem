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

    /**
     * Fjerner hylden fra reolen. Checker om hylden ikke er null
     * Hvis hylde ikke er null, fjerner den alle fad p[ hylden, ved et for-each loop
     * @param hyldeId ID på den hylde som skal fjernes
     */
    public void removeHylde(int hyldeId) {
        hyldeMap.remove(hyldeId);
    }


    public int getReolId() {
        return reolId;
    }

    public List<Hylde> getHyldeList() {
        return new ArrayList<>(hyldeMap.values());
    }

    public Hylde getHylde(int hyldeId) {
        return hyldeMap.get(hyldeId);
    }

    public HashMap<Integer, Hylde> getHyldeMap() {
        return hyldeMap;
    }

    @Override
    public String toString() {
        return "Reol: " + reolId;
    }
}

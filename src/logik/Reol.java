package logik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
        Hylde hylde = hyldeMap.remove(hyldeId);
        if (hylde != null) {
            Iterator<Fad> iterator = hylde.getFadPåHyldeMap().values().iterator();
            while (iterator.hasNext()) {
                Fad fadPåHylde = iterator.next();
                iterator.remove();
                hylde.removeFadFraHylde(fadPåHylde.getFadId());
            }
        }
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

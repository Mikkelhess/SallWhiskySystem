package logik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Hylde {

    private static int idCounter = 1;
    private final int hyldeId;
    private HashMap<Integer, Fad> fadPåHyldeMap = new HashMap<>();

    public Hylde() {
        this.hyldeId = idCounter;
        idCounter++;
    }

    public void addFadTilHylde(Fad fad) {
        fadPåHyldeMap.put(fad.getFadId(), fad);
    }

    public void removeFadFraHylde(int fadId) {
        fadPåHyldeMap.remove(fadId);
    }

    public int getHyldeId() {
        return hyldeId;
    }

    public List<Fad> getFadPåHyldeList() {
        return new ArrayList<>(fadPåHyldeMap.values());
    }

    public Fad getFadPåHylde(int fadId) {
        for (Fad fad : getFadPåHyldeList()) {
            if (fadId == fad.getFadId()) {
                return fad;
            }
        }
        return null;
    }

}

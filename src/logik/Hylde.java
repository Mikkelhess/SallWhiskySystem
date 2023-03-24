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

    public HashMap<Integer, Fad> getFadPåHyldeMap() {
        return fadPåHyldeMap;
    }


    @Override
    public String toString() {
        return "Hylde " + hyldeId;
    }
}

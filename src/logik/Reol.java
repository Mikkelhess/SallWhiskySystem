package logik;

import java.util.HashMap;

public class Reol {

    private static int idCounter = 1;
    private final int reolId;
    private HashMap<Integer, Hylde> hylder = new HashMap<>();

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
        hylder.put(hylde.getHyldeId(), hylde);
    }

    public void removeHylde(int hyldeId) {
        hylder.remove(hyldeId);
    }

    public int getReolId() {
        return reolId;
    }


}

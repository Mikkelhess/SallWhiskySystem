package logik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lager {

    private static int idCounter = 1;
    private final int lagerId;
    private HashMap<Integer, Reol> reolMap = new HashMap<>();

    public Lager() {
        this.lagerId = idCounter;
        idCounter++;
    }

    public Reol createReol() {
        Reol reol = new Reol();
        addReol(reol);
        return reol;
    }

    public void addReol(Reol reol) {
        reolMap.put(reol.getReolId(), reol);
    }

    public void removeReol(int reolId) {
        Reol reol = reolMap.remove(reolId);
        if (reol != null) {
            for (Hylde hylde : reol.getHyldeMap().values()) {
                reol.removeHylde(hylde.getHyldeId());
            }
        }
    }

    public int getLagerId() {
        return lagerId;
    }

    public HashMap<Integer,Reol> getReolMap() {
        return reolMap;
    }

    public Reol getReol(int reolId) {
       return reolMap.get(reolId);
    }

    @Override
    public String toString() {
        return "Lager " + lagerId;
    }
}

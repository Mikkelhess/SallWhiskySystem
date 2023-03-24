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
        reolMap.remove(reolId);
    }
    public int getLagerId() {
        return lagerId;
    }

    public List<Reol> getReolList() {
        return new ArrayList<>(reolMap.values());
    }

    public Reol getReol(int reolId) {
        for (Reol reol : getReolList()) {
            if (reolId == reol.getReolId()) {
                return reol;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Lager " + lagerId;
    }
}

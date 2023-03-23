package logik;

import java.util.HashMap;

public class Lager {

    private static int idCounter = 1;
    private final int lagerId;
    private HashMap<Integer, Reol> reoler = new HashMap<>();

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
        reoler.put(reol.getReolId(), reol);
    }

    public void removeReol(int reolId) {
        reoler.remove(reolId);
    }

    public int getLagerId() {
        return lagerId;
    }


}

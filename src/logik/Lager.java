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
    /**
     * Fjerner reolen fra lageret. Checker om reolen ikke er null
     * Hvis reol ikke er null, fjerner den alle hylder fra reolen, ved et for-each loop
     * @param reolId ID på den reol som skal fjernes
     */
    public void removeReol(int reolId) {
        Reol reol = reolMap.remove(reolId);
        if (reol != null) {
            for (Hylde hylde : reol.getHyldeList()) {
                reol.removeHylde(hylde.getHyldeId());
            }
        }
    }
    public int getLagerId() {
        return lagerId;
    }

    public List<Reol> getReolList() {
        return new ArrayList<>(reolMap.values());
    }

    public Reol getReol(int reolId) {
        return reolMap.get(reolId);
    }

}

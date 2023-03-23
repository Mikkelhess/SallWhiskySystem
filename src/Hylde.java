import java.util.HashMap;

public class Hylde {

    private static int idCounter = 1;
    private final int hyldeId;
    private HashMap<Integer, Fad> fadMap = new HashMap<>();

    public Hylde() {
        this.hyldeId = idCounter;
        idCounter++;
    }

    public Fad createFad(double størrelse, FadType fadType) {
        Fad fad = new Fad(størrelse, fadType);
        addFad(fad);
        return fad;
    }

    public void addFad(Fad fad) {
        fadMap.put(fad.getFadId(), fad);
    }

    public void removeFad(int fadId) {
        fadMap.remove(fadId);
    }

    public int getHyldeId() {
        return hyldeId;
    }




}

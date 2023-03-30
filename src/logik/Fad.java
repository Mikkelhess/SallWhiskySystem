package logik;

import java.util.HashMap;

public class Fad {

    private double fadLiter;
    private HashMap<Integer, Destillat> destillatMap = new HashMap<>();
    private static int idCounter = 1;
    private final int fadId;
    private FadType fadType;

    public Fad(double fadLiter, FadType fadType) {
        this.fadType = fadType;
        this.fadLiter = fadLiter;
        fadId = idCounter;
        idCounter++;
    }

    public void addDestillat(int newMakeNummer, Destillat destillat) {
        destillatMap.put(newMakeNummer, destillat);
    }

    public void removeDestillat(int newMakeNummer) {
        destillatMap.remove(newMakeNummer);
    }

    public int getFadId() {
        return fadId;
    }

    public FadType getFadType() {
        return fadType;
    }

    public double getFadLiter() {
        return fadLiter;
    }

    @Override
    public String toString() {
        return "Fad " + fadId + ": " + fadType + ". " + fadLiter + " liter i fadet.";
    }
}

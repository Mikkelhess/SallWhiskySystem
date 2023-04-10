package logik;

import java.util.HashMap;

public class Fad {

    private HashMap<Integer, CompositeDestillat> destillatMap = new HashMap<>();
    private HashMap<String, LeafDestillat> leafDestillatMap = new HashMap<>();
    private double fadLiter;
    private static int idCounter = 1;
    private final int fadId;
    private FadType fadType;
    private String leverandør;


    public Fad(double fadLiter, FadType fadType, String leverandør) {
        this.fadType = fadType;
        this.fadLiter = fadLiter;
        fadId = idCounter;
        idCounter++;
        this.leverandør = leverandør;
    }

    public void addDestillat(int newMakeNummer, CompositeDestillat compositeDestillat) {
        destillatMap.put(newMakeNummer, compositeDestillat);
    }

    public void addLeafDestillat(String leafNewMakeNummer, LeafDestillat leafDestillat) {
        leafDestillatMap.put(leafNewMakeNummer, leafDestillat);
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

    public HashMap<Integer, CompositeDestillat> getDestillatMap() {
        return destillatMap;
    }

    public HashMap<String, LeafDestillat> getLeafDestillatMap() {
        return leafDestillatMap;
    }

    @Override
    public String toString() {
        return "Fad " + fadId + ": " + fadType + ". Kapacitet: " + fadLiter + " liter. " + "Leverandør: " + leverandør;
    }
}

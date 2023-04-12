package logik;

import java.time.LocalDate;
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

    public void addLeafDestillat(String leafNewMakeNummer, LeafDestillat leafDestillat, LocalDate lagringsDato) {
        leafDestillatMap.put(leafNewMakeNummer, leafDestillat);
        leafDestillat.sætFad(this);
        leafDestillat.setLagringsDato(lagringsDato);
    }

    public void removeLeafDestillat(String leafnewMakeNummer) {
        destillatMap.remove(leafnewMakeNummer);
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

    public String getLeverandør() {
        return leverandør;
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

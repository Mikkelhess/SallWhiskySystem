package logik;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Fad {

    private HashMap<String, LeafDestillat> leafDestillatMap = new HashMap<>();
    private ArrayList<FadHistorik> fadHistorikList = new ArrayList<>();
    private double fadLiter;
    private static int idCounter = 1;
    private final int fadId;
    private FadType fadType;
    private String leverandør;
    private Fad omhældtFad;


    public Fad(double fadLiter, FadType fadType, String leverandør) {
        this.fadType = fadType;
        this.fadLiter = fadLiter;
        fadId = idCounter;
        idCounter++;
        this.leverandør = leverandør;
    }

    public void addLeafDestillat(String leafNewMakeNummer, LeafDestillat leafDestillat, LocalDate tilføjetDato) {
        leafDestillatMap.put(leafNewMakeNummer, leafDestillat);
        leafDestillat.setPåFad(true);
        FadHistorik fadHistorik = new FadHistorik(this, leafDestillat, tilføjetDato, null);
        this.addFadHistorik(fadHistorik);
    }

    public void removeLeafDestillat(String leafnewMakeNummer, LocalDate fjernetDato) {
        FadHistorik fadHistorik = new FadHistorik(this, leafDestillatMap.get(leafnewMakeNummer), null, fjernetDato);
        addFadHistorik(fadHistorik);
        leafDestillatMap.remove(leafnewMakeNummer);

    }

    public void addFadHistorik(FadHistorik fadHistorik) {
        fadHistorikList.add(fadHistorik);
    }

    public int getFadId() {
        return fadId;
    }

    public FadType getFadType() {
        return fadType;
    }

    public void setOmhældtFad(Fad omhældtFad) {
        this.omhældtFad = omhældtFad;
    }

    public Fad getOmhældtFad() {
        return omhældtFad;
    }

    public double getFadLiter() {
        return fadLiter;
    }

    public String getLeverandør() {
        return leverandør;
    }
    public HashMap<String, LeafDestillat> getLeafDestillatMap() {
        return leafDestillatMap;
    }

    public ArrayList<FadHistorik> getFadHistorikList() {
        return new ArrayList<>(fadHistorikList);
    }

    @Override
    public String toString() {
        return "Fad " + fadId + ": " + fadType + ". Kapacitet: " + fadLiter + " liter ";
    }
}

package logik;

import controller.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Destillering {

    private HashMap<String, CompositeDestillat> destillatMap = new HashMap<>();
    private int destilleringId;
    private static int idCounter = 1;
    private String medarbejderNavn;
    private LocalDate startDato;
    private LocalDate slutDato;
    private String maltBatch;
    private String kornsort;
    private double totalLiter;
    private double liter;
    private String rygeMateriale;
    private String kommentar;


    public Destillering(String medarbejderNavn, LocalDate startDato, LocalDate slutDato, String maltBatch, String kornsort, double totalLiter, String rygeMateriale, String kommentar) {
        this.medarbejderNavn = medarbejderNavn;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.maltBatch = maltBatch;
        this.kornsort = kornsort;
        this.rygeMateriale = rygeMateriale;
        this.kommentar = kommentar;

        this.totalLiter = totalLiter;
        this.liter = 0;
        destilleringId = idCounter;
        idCounter++;

        udregnBrugteLiter();
    }

    public CompositeDestillat createDestillat(double liter, double alkoholProcent) {
        CompositeDestillat compositeDestillat = new CompositeDestillat(alkoholProcent);
        compositeDestillat.setTotalLiter(liter);
        addDestillat(compositeDestillat.getNewMakeNummer(), compositeDestillat);

        liter = udregnBrugteLiter(); // update the value of liter
        setLiter(liter); // set the new value of liter
        return compositeDestillat;
    }

    public void addDestillat(String newMakeNummer, CompositeDestillat compositeDestillat) {
        destillatMap.put(newMakeNummer, compositeDestillat);
        Controller.addDestillat(compositeDestillat);
    }

    public void removeDestillat(String newMakeNummer){
        CompositeDestillat compositeDestillat = destillatMap.remove(newMakeNummer);
        Controller.removeDestillat(compositeDestillat);
    }

    public double udregnBrugteLiter() {
        double liter = 0;
        for (CompositeDestillat composite : destillatMap.values()) {
            liter += composite.getTotalLiter();
        }
        return totalLiter - liter;
    }

    //bliver den brugt
    public List<LeafDestillat> getAllLeaves() {
        List<LeafDestillat> allLeaves = new ArrayList<>();
        for (CompositeDestillat composite : getDestillatMap().values()) {
            allLeaves.addAll(composite.getLeaves());
        }
        return allLeaves;
    }

    public int getDestilleringId() {
        return destilleringId;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public void setLiter(double liter) {
        this.liter = liter;
    }

    public String getMedarbejderNavn() {
        return medarbejderNavn;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public String getMaltBatch() {
        return maltBatch;
    }

    public String getKornsort() {
        return kornsort;
    }

    public double getTotalLiter() {
        return totalLiter;
    }

    public String getRygeMateriale() {
        return rygeMateriale;
    }

    public String getKommentar() {
        return kommentar;
    }

    public double getLiter() {
        return liter;
    }

    public HashMap<String, CompositeDestillat> getDestillatMap() {
        return new HashMap<>(destillatMap);
    }

    @Override
    public String toString() {
        return "Destillering " + destilleringId + ": " + udregnBrugteLiter() + " / " + totalLiter + " liter";
    }
}

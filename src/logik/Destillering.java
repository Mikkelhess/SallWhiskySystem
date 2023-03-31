package logik;

import storage.Storage;

import java.time.LocalDate;
import java.util.HashMap;

public class Destillering {

    private HashMap<Integer, Destillat> destillatMap = new HashMap<>();
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
        this.totalLiter = totalLiter;
        this.rygeMateriale = rygeMateriale;
        this.kommentar = kommentar;

        destilleringId = idCounter;
        idCounter++;
    }


    public Destillat createDestillat(double liter, double alkoholProcent) {
        Destillat destillat = new Destillat(liter, alkoholProcent);
        addDestillat(destillat.getNewMakeNummer(), destillat);
        udregnLiter();
        return destillat;
    }

    public void addDestillat(int newMakeNummer, Destillat destillat) {
        destillatMap.put(newMakeNummer, destillat);
    }

    public void removeDestillat(int newMakeNummer){
        destillatMap.remove(newMakeNummer);
    }

    public double udregnLiter() {
        double liter = 0;

        for (Destillat destillat : destillatMap.values()) {
            liter += destillat.getLiter();
        }
        setLiter(totalLiter-liter);
        return totalLiter - liter;
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

    public HashMap<Integer, Destillat> getDestillatMap() {
        return new HashMap<>(destillatMap);
    }

    @Override
    public String toString() {
        return "Destillering " + destilleringId + ": " + liter + " / " + totalLiter + " liter";
    }
}

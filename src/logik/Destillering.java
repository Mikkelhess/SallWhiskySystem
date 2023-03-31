package logik;

import java.time.LocalDate;
import java.util.HashMap;

public class Destillering {

    private HashMap<Integer, Destillat> destillatMap = new HashMap<>();
    private String medarbejderNavn;
    private LocalDate startDato;
    private LocalDate slutDato;
    private String maltBatch;
    private String kornsort;
    private double totalLiter;
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
    }


    public Destillat createDestillat(double liter, int NewMakeNummer, double alkoholProcent) {
        Destillat destillat = new Destillat(liter, NewMakeNummer, alkoholProcent);
        addDestillat(destillat.getNewMakeNummer(), destillat);
        return destillat;
    }

    public boolean checkTilgængeligLiter(double liter) {
        // TODO
    }

    public void addDestillat(int newMakeNummer, Destillat destillat) {
        destillatMap.put(newMakeNummer, destillat);
    }

    public void removeDestillat(int newMakeNummer){
        destillatMap.remove(newMakeNummer);
    }





}

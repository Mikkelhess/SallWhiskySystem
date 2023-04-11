package logik;

import version12.WhiskyHistorie;

import java.util.ArrayList;

public class Whisky {

    private static int idCounter = 1;
    private final int whiskyId;
    private ArrayList<Fad> whiskyFad;
    private double alkoholProcent;
    private double liter;
    private double vandML;
    private String whiskyType;
    private String beskrivelse;

    public Whisky(double liter, String beskrivelse,  ArrayList<Fad> whiskyFad) {

        this.whiskyFad = whiskyFad;
        this.liter = liter;
        this.beskrivelse = beskrivelse;
        this.whiskyType = whiskyType;

        this.whiskyId = idCounter;
        idCounter++;

        //if ()

    }

    public int getWhiskyId() {
        return whiskyId;
    }

    public double getLiter() {
        return liter;
    }

    public void setLiter(double liter) {
        this.liter = liter;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }


}

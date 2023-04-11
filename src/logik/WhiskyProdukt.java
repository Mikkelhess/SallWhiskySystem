package logik;

import java.util.ArrayList;

public class WhiskyProdukt {

    private static int idCounter = 1;
    private final int whiskyId;
    private ArrayList<Fad> whiskyFad;
    private double alkoholProcent;
    private double liter;
    private double vandML;
    private String whiskyType;
    private String beskrivelse;
    private String vandLokation;
    private int antalFlasker;
    private double flaskeStørrelse;

    public WhiskyProdukt(String whiskyType, double liter, int antalFlasker, double flaskeStørrelse, String vandLokation, double vandML, String beskrivelse, ArrayList<LeafDestillat> destillatListe) {

        this.whiskyType = whiskyType;
        this.liter = liter;
        this.vandML = vandML;
        this.beskrivelse = beskrivelse;
        this.vandLokation = vandLokation;
        this.antalFlasker = antalFlasker;
        this.flaskeStørrelse = flaskeStørrelse;

        this.whiskyId = idCounter;
        idCounter++;
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

    @Override
    public String toString() {
        return "Whisky Produkt " + whiskyId + ", Liter: " + liter + ", Antal Flasker: " + antalFlasker;
    }
}

package logik;

import java.time.LocalDate;
import java.util.ArrayList;

public class LeafDestillat implements DestillatComponent {

    private Fad fad;
    private double liter;
    private String leafNewMakeNummer;
    private double alkoholProcent;
    private LocalDate lagringsDato;
    private LocalDate påfyldningsDato; //dato for påfyldning til whisky
    private LocalDate omhældningsDato;
    private boolean påFad;

    public LeafDestillat(double liter, String leafNewMakeNummer, double alkoholProcent) {
        this.liter = liter;
        this.leafNewMakeNummer = leafNewMakeNummer;
        this.alkoholProcent = alkoholProcent;
    }

    @Override
    public double getLiter() {
        return liter;
    }

    @Override
    public void setLiter(double liter) {
        this.liter = liter;
    }

    public void setLagringsDato(LocalDate lagringsDato) {
        this.lagringsDato = lagringsDato;
    }

    public void setOmhældningsDato(LocalDate omhældningsDato) {
        this.omhældningsDato = omhældningsDato;
    }

    public LocalDate getLagringsDato() {
        return lagringsDato;
    }

    public LocalDate getOmhældningsDato() {
        return omhældningsDato;
    }

    public String getLeafNewMakeNummer() {
        return leafNewMakeNummer;
    }

    @Override
    public void add(DestillatComponent component) {
        throw new UnsupportedOperationException("Cannot add a child to a leaf");
    }

    @Override
    public void remove(DestillatComponent component) {
        throw new UnsupportedOperationException("Cannot remove a child from a leaf");
    }

    @Override
    public DestillatComponent getChild(int index) {
        throw new UnsupportedOperationException("Cannot get a child from a leaf");
    }

    @Override
    public String getNewMakeNummer() {
        return leafNewMakeNummer;
    }

    @Override
    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public void sætFad(Fad fad) {
        this.fad = fad;
    }

    public Fad getFad() {
        return fad;
    }



    public boolean påFad() {
        return påFad;
    }

    public void setPåFad(boolean påFad) {
        this.påFad = påFad;
    }
    @Override
    public String toString() {
        return "Destillat-del " + leafNewMakeNummer + ": " + liter + " liter ";
    }


}

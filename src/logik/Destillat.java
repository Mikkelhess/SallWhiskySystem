package logik;

import java.util.HashMap;

public class Destillat {

    private double liter;
    private int newMakeNummer;
    private static int newMakeCounter = 1;
    private double alkoholProcent;

    public Destillat(double liter, int newMakeNummer, double alkoholProcent) {
        this.liter = liter;
        this.alkoholProcent = alkoholProcent;

        this.newMakeNummer = newMakeCounter;
        newMakeCounter++;
    }

    public double getLiter() {
        return liter;
    }

    public int getNewMakeNummer() {
        return newMakeNummer;
    }

    public static int getNewMakeCounter() {
        return newMakeCounter;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }
}

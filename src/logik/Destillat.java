package logik;

import java.time.LocalDate;
import java.util.HashMap;

public class Destillat {

    private final double maxLiter;
    private double currentLiter;
    private final int newMakeNummer;
    private static int newMakeCounter = 1;
    private double alkoholProcent;


    public Destillat(double maxLiter, double alkoholProcent) {
        this.maxLiter = maxLiter;
        this.alkoholProcent = alkoholProcent;
        this.currentLiter = maxLiter;

        this.newMakeNummer = newMakeCounter;
        newMakeCounter++;
    }

    public void hældPåFad(double liter) {
        this.currentLiter -= liter;
    }

    public double getMaxLiter() {
        return maxLiter;
    }

    public double getCurrentLiter() {
        return currentLiter;
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

    @Override
    public String toString() {
        return "Destillat " + newMakeNummer + ": " + currentLiter + " / " + maxLiter + " liter," + " Alkoholprocent: " + alkoholProcent;
    }
}

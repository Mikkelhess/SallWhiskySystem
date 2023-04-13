package logik;


//skal have lagringsdato, omhældnings dato (måske if sætninger der determinerer påfyldning/omhældning)
//add, remove, omhældning


import logik.Fad;
import logik.LeafDestillat;

import java.time.LocalDate;

public class FadHistorik {
    private Fad fad;
    private LeafDestillat destillat;
    private LocalDate tilføjetDato;
    private LocalDate fjernetDato;

    public FadHistorik(Fad fad, LeafDestillat destillat, LocalDate tilføjetDato, LocalDate fjernetDato) {
        this.fad = fad;
        this.destillat = destillat;
        this.tilføjetDato = tilføjetDato;
        this.fjernetDato = fjernetDato;
    }

    public Fad getFad() {
        return fad;
    }

    public LeafDestillat getDestillat() {
        return destillat;
    }

    public void setTilføjetDato(LocalDate tilføjetDato) {
        this.tilføjetDato = tilføjetDato;
    }

    public LocalDate getTilføjetDato() {
        return tilføjetDato;
    }

    public LocalDate getFjernetDato() {
        return fjernetDato;
    }

    public void setFjernetDato(LocalDate fjernetDato) {
        this.fjernetDato = fjernetDato;
    }
}
package logik;

import com.sun.jdi.Value;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * Looper igennem destillatMap vha. for-each loop
     * og checker om hvorvidt liter er mindre eller lig med litreBrugt - totalLiter
     * @param liter den værdi som skal tjekkes imod litre som er tilgængelige
     * @return true eller false alt efter om der er litre tilgængelig
     */
    public boolean checkIfTilgængeligLiter(double liter) {
        double litreBrugt = 0;
        for (Map.Entry<Integer, Destillat> entry : destillatMap.entrySet()) {
            litreBrugt += entry.getValue().getLiter();
        }
        if (liter <= (totalLiter - litreBrugt)) {
            return true;
        }
        return false;
    }


    public void addDestillat(int newMakeNummer, Destillat destillat) {
        destillatMap.put(newMakeNummer, destillat);
    }

    public void removeDestillat(int newMakeNummer){
        destillatMap.remove(newMakeNummer);
    }





}

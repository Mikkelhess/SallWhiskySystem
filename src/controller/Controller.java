package controller;

import logik.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Controller {


    public static WhiskyProdukt opretWhiskyProdukt(String whiskyType, double liter, int
            antalFlasker, double flaskeStørrelse, String vandLokation, double vandLiter, String beskrivelse, ArrayList<LeafDestillat> destillatListe) {
        WhiskyProdukt whiskyProdukt = new WhiskyProdukt(whiskyType, liter, antalFlasker, flaskeStørrelse, vandLokation, vandLiter, beskrivelse, destillatListe);
        addWhisky(whiskyProdukt);
        return whiskyProdukt;
    }

    public static void addWhisky(WhiskyProdukt whiskyProdukt){
        Storage.getInstance().addWhisky(whiskyProdukt);
    }


    /**
     * Opretter et nyt lager
     * @return lager
     */
    public static Lager opretLager() {
        Lager lager = new Lager();
        Storage.getInstance().addLager(lager);
        return lager;
    }

    /**
     * Opretter et fad
     * @param størrelse på fad i liter
     * @param fadType Hvad der har lagret i fadet inden, eks. sherry
     * @return fad
     */
    public static Fad opretFad(double størrelse, FadType fadType, String leverandør) {
        if (størrelse <= 0 || fadType == null) {throw new NullPointerException("Angiv korrekte oplysninger.");}

        Fad fad = new Fad(størrelse, fadType, leverandør);
        Storage.getInstance().addFad(fad);
        return fad;
    }

    /**
     * Opretter en ny reol
     * @param lager der hvor reolen skal oprettes
     * @return reol
     */
    public static Reol opretReol(Lager lager) {
        if (lager == null) {throw new NullPointerException("Angiv et lager.");}
        Reol reol = lager.createReol();
        return reol;
    }

    /**
     * Opretter en ny hylde
     * @param reol der hvor hylden skal oprettes
     * @return hylde
     */
    public static Hylde opretHylde(Reol reol) {
        if (reol == null) {throw new NullPointerException("Angiv en reol.");}
        Hylde hylde = reol.createHylde();
        return hylde;
    }

    public static Destillering opretDestillering(String medarbejderNavn, LocalDate startDato, LocalDate slutdato, String maltBatch, String kornsort, double totalLiter, String rygemateriale, String kommentar) {
        Destillering destillering = new Destillering(medarbejderNavn, startDato, slutdato, maltBatch, kornsort, totalLiter, rygemateriale, kommentar);
        Storage.getInstance().addDestillering(destillering);
        return destillering;
    }

    public static void removeDestillering(Destillering destillering) {
        getDestilleringMap().values().remove(destillering);
    }

    /**
     * Tilføjer et fad til en hylde
     * @param hylde hvor fadet skal tilføjes
     * @param fad der tilføjes
     */
    public static void addFadtilHylde(Hylde hylde, Fad fad) {
        if (hylde == null || fad == null) {throw new NullPointerException("Angiv korrekt information.");}

        hylde.addFadTilHylde(fad);
    }

    public static Lager getLager(int lagerId) {
        return Storage.getInstance().getLagerMap().get(lagerId);
    }
    public static void removeLager(Lager lager) {
        Storage.getInstance().removeLager(lager);
    }

    // Mangler begrænsninger
    public static Fad getFad(int fadId) {
        return getFadMap().get(fadId);
    }

    public static void removeFad(int lagerId, int reolId, int hyldeId, int fadId) {
        Hylde hylde = getHylde(lagerId, reolId, hyldeId);
        if (hylde != null) {
            hylde.removeFadFraHylde(fadId);
        }
    }

    public static Reol getReol(int lagerId, int reolId) {
        Lager lager = getLager(lagerId);
        return lager.getReol(reolId);
    }

    // Mangler begrænsninger
    public static void removeReol(int lagerId, int reolId) {
        Lager lager = getLager(lagerId);
        if (lager != null) {
            lager.removeReol(reolId);
        }
    }

    public static Hylde getHylde(int lagerId, int reolId, int hyldeId) {
        Reol reol = getReol(lagerId, reolId);
        return reol.getHylde(hyldeId);
    }

    // Mangler begrænsninger
    public static void removeHylde(int lagerId, int reolId, int hyldeId) {
        Reol reol = getReol(lagerId, reolId);
        reol.removeHylde(hyldeId);
    }

    // Mangler begrænsninger
    public static void removeFadFraHylde(int lagerId, int reolId, int hyldeId, int fadId) {
        Hylde hylde = getHylde(lagerId, reolId, hyldeId);
        hylde.removeFadFraHylde(fadId);
    }


    public static HashMap<Integer,Lager> getLagerMap() {
        return Storage.getInstance().getLagerMap();
    }

    public static HashMap<Integer,Fad> getFadMap() {
        return Storage.getInstance().getFadMap();
    }

    public static HashMap<Integer, WhiskyProdukt> getWhiskyMap() {
        return Storage.getInstance().getWhiskyMap();
    }

    public static List<Fad> getFadUdenHylde() {
        ArrayList<Fad> fadUdenHyldeListe = new ArrayList<>(getFadMap().values());
        for (Lager lager : Storage.getInstance().getLagerMap().values()) {
            for (Reol reol : lager.getReolMap().values()) {
                for (Hylde hylde : reol.getHyldeMap().values()) {
                    for (Fad fad : hylde.getFadPåHyldeMap().values()) {
                        fadUdenHyldeListe.remove(fad);
                    }
                }
            }
        }
        return fadUdenHyldeListe;
    }

    public static void addDestillat(CompositeDestillat compositeDestillat){
        Storage.getInstance().addDestillat(compositeDestillat);
    }

    public static void removeDestillat(CompositeDestillat compositeDestillat){
        Storage.getInstance().removeDestillat(compositeDestillat);
    }

    public static HashMap<String, CompositeDestillat> getDestillatMap(){
        return Storage.getInstance().getDestillatMap();
    }

    public static void addBrugtDestillat(LeafDestillat leafDestillat) {
        Storage.getInstance().addBrugtDestillat(leafDestillat);
    }

    public static boolean isLeafDestillatUsed(LeafDestillat leafDestillat) {
        return Storage.getInstance().getBrugteDestillater().contains(leafDestillat);
    }

    public static HashMap<Integer,Reol> getReolMap(int lagerId) {
        Lager lager = getLager(lagerId);
        return lager.getReolMap();
    }

    public static HashMap<Integer,Hylde> getHyldeMap(int lagerId, int reolId) {
        Reol reol = getReol(lagerId, reolId);
        return reol.getHyldeMap();
    }

    public static HashMap<Integer,Fad> getFadPåHyldeMap(int lagerId, int reolId, int hyldeId) {
        Hylde hylde = getHylde(lagerId, reolId, hyldeId);
        return hylde.getFadPåHyldeMap();
    }

    public static HashMap<Integer,Destillering> getDestilleringMap() {
        return Storage.getInstance().getDestilleringMap();
    }


//--------------------------------------------------------------------------------------

    public static void initStorage() {
        Lager lager = opretLager();
        Lager lager2 = opretLager();

        Reol reol = opretReol(lager);
        Reol reol2 = opretReol(lager2);

        Hylde hylde = opretHylde(reol);
        Hylde hylde2 = opretHylde(reol);
        Hylde hylde3 = opretHylde(reol);
        Hylde hylde4 = opretHylde(reol2);
        Hylde hylde5 = opretHylde(reol2);
        Hylde hylde6 = opretHylde(reol2);

        Fad fad1 = opretFad(500, FadType.SHERRY, "Bodegas Tradicion");
        Fad fad2 = opretFad(500, FadType.PORTVIN, "Cold Hand Winery");
        Fad fad3 = opretFad(300, FadType.BOURBON, "Stauning Whisky");
        Fad fad4 = opretFad(2000, FadType.COGNAC, "Hennessy");
        Fad fad5 = opretFad(800, FadType.RØDVIN, "Château Margaux");
        Fad fad6 = opretFad(1000, FadType.COGNAC, "Hennessy");
        Fad fad7 = opretFad(5000, FadType.RØDVIN,"Skærsøgaard Vin" );
        Fad fad8 = opretFad(3000, FadType.BOURBON, "Four Roses");
        Fad fad9 = opretFad(500, FadType.COGNAC, "Hennessy");
        Fad fad10 = opretFad(2000, FadType.RØDVIN, "Skærsøgaard Vin");
        Fad fad11 = opretFad(1000, FadType.SHERRY, "Bodegas Tradicion");
        Fad fad12 = opretFad(2500, FadType.RØDVIN, "Skærsøgaard Vin");


        addFadtilHylde(hylde, fad1);
        addFadtilHylde(hylde, fad2);
        addFadtilHylde(hylde2, fad3);
        addFadtilHylde(hylde3, fad4);
        addFadtilHylde(hylde3, fad5);
        addFadtilHylde(hylde4, fad6);
        addFadtilHylde(hylde4, fad7);
        addFadtilHylde(hylde5, fad8);
        addFadtilHylde(hylde6, fad9);
        addFadtilHylde(hylde6, fad10);

        Destillering destillering1 = opretDestillering("Snævar", LocalDate.of(2023, 3, 30), LocalDate.of(2023, 4, 1), "Batch 1", "Evergreen", 800.0, "Tørv", "Sådan venner");
        Destillering destillering2 = opretDestillering("Martin", LocalDate.of(2019, 10, 10), LocalDate.of(2023, 10, 11), "Batch 2", "Stairway", 5000.0, "Halm", "Den er god");

        CompositeDestillat compositeDestillat1 = destillering1.createDestillat(100.0, 60);
        LeafDestillat leafDestillat1 = compositeDestillat1.createLeaf(20);
        LeafDestillat leafDestillat2 = compositeDestillat1.createLeaf(30);
        CompositeDestillat compositeDestillat2 = destillering1.createDestillat(150.0, 65);
        CompositeDestillat compositeDestillat3 = destillering1.createDestillat(65.0,60);
        LeafDestillat leafDestillat3 = compositeDestillat3.createLeaf(15);

        CompositeDestillat compositeDestillat4 = destillering2.createDestillat(500.0, 60);
        LeafDestillat leafDestillat4 = compositeDestillat4.createLeaf(50);
        LeafDestillat leafDestillat5 = compositeDestillat4.createLeaf(100);
        CompositeDestillat compositeDestillat5 = destillering2.createDestillat(300.0, 70);
        LeafDestillat leafDestillat6 = compositeDestillat5.createLeaf(50);
        CompositeDestillat compositeDestillat6 = destillering2.createDestillat(1000.0, 65);
        LeafDestillat leafDestillat7 = compositeDestillat6.createLeaf(200);
        LeafDestillat leafDestillat8 = compositeDestillat6.createLeaf(100);

        CompositeDestillat compositeDestillat7 = destillering2.createDestillat(500,60);
        LeafDestillat leafDestillat9 = compositeDestillat7.createLeaf(30);
        LeafDestillat leafDestillat10 = compositeDestillat7.createLeaf(30);
        LeafDestillat leafDestillat11 = compositeDestillat7.createLeaf(60);
        LeafDestillat leafDestillat12 = compositeDestillat7.createLeaf(100);
        LeafDestillat leafDestillat13 = compositeDestillat7.createLeaf(150);

        LocalDate lagringsDato1 = LocalDate.of(2018, 5, 15);
        LocalDate lagringsDato2 = LocalDate.of(2018, 3, 15);
        LocalDate lagringsDato3 = LocalDate.of(2016, 8, 21);

        fad1.addLeafDestillat(leafDestillat1.getLeafNewMakeNummer(), leafDestillat1, lagringsDato1);
        fad1.addLeafDestillat(leafDestillat2.getLeafNewMakeNummer(), leafDestillat2, lagringsDato2);
        fad3.addLeafDestillat(leafDestillat3.getLeafNewMakeNummer(), leafDestillat3, lagringsDato3);
        fad4.addLeafDestillat(leafDestillat8.getLeafNewMakeNummer(), leafDestillat8, lagringsDato1);
        fad4.addLeafDestillat(leafDestillat9.getLeafNewMakeNummer(), leafDestillat9, lagringsDato2);
        fad6.addLeafDestillat(leafDestillat10.getLeafNewMakeNummer(), leafDestillat10, lagringsDato2);
        fad8.addLeafDestillat(leafDestillat11.getLeafNewMakeNummer(), leafDestillat11, lagringsDato3);
        fad9.addLeafDestillat(leafDestillat12.getLeafNewMakeNummer(), leafDestillat12, lagringsDato3);

        ArrayList<LeafDestillat> destillatListe = new ArrayList<>();
        destillatListe.add(leafDestillat1);
        destillatListe.add(leafDestillat2);
        destillatListe.add(leafDestillat3);

        WhiskyProdukt whiskyProdukt = Controller.opretWhiskyProdukt("Single Malt", 750, 1000, 0.75, "Begravede dale i Sall", 50, "Interessante smagsprofiler", destillatListe);

    }
}
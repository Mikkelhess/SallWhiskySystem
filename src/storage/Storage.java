package storage;

import logik.*;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Lager> lagerList = new ArrayList<>();
    private List<Fad> fadList = new ArrayList<>();

    public Lager addLager(Lager lager) {
        lagerList.add(lager);
        return lager;
    }

    public Fad addFad(Fad fad) {
        fadList.add(fad);
        return fad;
    }

    public void removeLager(Lager lager) {
        lagerList.remove(lager);
    }

    public void removeFad(Fad fad) {
        fadList.remove(fad);
    }

    public List<Lager> getLagerList() {
        return new ArrayList<Lager>(lagerList);
    }

    public List<Fad> getFadList() {
        return new ArrayList<Fad>(fadList);
    }

}


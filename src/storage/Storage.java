package storage;

import logik.Fad;
import logik.Hylde;
import logik.Lager;
import logik.Reol;

import java.util.HashMap;

    public class Storage {
        private HashMap<Integer, Lager> lagerMap = new HashMap<>();
        private HashMap<Integer, Reol> reolMap = new HashMap<>();
        private HashMap<Integer, Hylde> hyldeMap = new HashMap<>();
        private HashMap<Integer, Fad> fadMap = new HashMap<>();


        public void addLager(Lager lager) {
            lagerMap.put(lager.getLagerId(), lager);
        }

        public void addReol(Reol reol) {
            reolMap.put(reol.getReolId(), reol);
        }

        public void addHylde(Hylde hylde) {
            hyldeMap.put(hylde.getHyldeId(), hylde);
        }

        public void addFad(Fad fad) {
            fadMap.put(fad.getFadId(), fad);
        }

        public void addFadTilHylde(Fad fad, Hylde hylde) {
            hylde.addFad(fad);
        }



        public Lager getLager(int lagerId) {
            return lagerMap.get(lagerId);
        }

        public void removeLager(int lagerId) {
            lagerMap.remove(lagerId);
        }

        public Reol getReol(int reolId) {
            return reolMap.get(reolId);
        }

        public void removeReol(int reolId) {
            reolMap.remove(reolId);
        }

        public Hylde getHylde(int hyldeId) {
            return hyldeMap.get(hyldeId);
        }

        public void removeHylde(int hyldeId) {
            reolMap.remove(hyldeId);
        }

        public Fad getFad(int fadId) {
            return fadMap.get(fadId);
        }

        public void removeFad(int fadId) {
            reolMap.remove(fadId);
        }

        public HashMap<Integer, Lager> getLagerMap() {
            return lagerMap;
        }

        public HashMap<Integer, Reol> getReolMap() {
            return reolMap;
        }

        public HashMap<Integer, Hylde> getHyldeMap() {
            return hyldeMap;
        }

        public HashMap<Integer, Fad> getFadMap() {
            return fadMap;
        }

    }


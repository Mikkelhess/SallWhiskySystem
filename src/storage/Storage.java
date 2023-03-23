package storage;

import logik.Fad;
import logik.Hylde;
import logik.Lager;
import logik.Reol;

import java.util.HashMap;

    public class Storage {
        private static final HashMap<Integer, Lager> lagerMap = new HashMap<>();
        private static final HashMap<Integer, Reol> reolMap = new HashMap<>();
        private static final HashMap<Integer, Hylde> hyldeMap = new HashMap<>();
        private static final HashMap<Integer, Fad> fadMap = new HashMap<>();


        public static void addLager(Lager lager) {
            lagerMap.put(lager.getLagerId(), lager);
        }

        public static void addReol(Reol reol) {
            reolMap.put(reol.getReolId(), reol);
        }

        public static void addHylde(Hylde hylde) {
            hyldeMap.put(hylde.getHyldeId(), hylde);
        }

        public static void addFad(Fad fad) {
            fadMap.put(fad.getFadId(), fad);
        }

        public static Lager getLager(int lagerId) {
            return lagerMap.get(lagerId);
        }

        public static void removeLager(int lagerId) {
            lagerMap.remove(lagerId);
        }

        public static Reol getReol(int reolId) {
            return reolMap.get(reolId);
        }

        public static void removeReol(int reolId) {
            reolMap.remove(reolId);
        }

        public static Hylde getHylde(int hyldeId) {
            return hyldeMap.get(hyldeId);
        }

        public static void removeHylde(int hyldeId) {
            reolMap.remove(hyldeId);
        }

        public static Fad getFad(int fadId) {
            return fadMap.get(fadId);
        }

        public static void removeFad(int fadId) {
            reolMap.remove(fadId);
        }


    }


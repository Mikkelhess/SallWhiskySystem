package storage;

import java.util.HashMap;

    public class Storage {
        private HashMap<Integer, Lager> lagerMap = new HashMap<>();

        public Storage() {

        }

        public Lager createLager() {
            Lager lager = new Lager();
            addLager(lager);
            return lager;
        }

        public void addLager(Lager lager) {
            lagerMap.put(lager.getLagerId(), lager);
        }


        public Lager getLager(int lagerId) {
            return lagerMap.get(lagerId);
        }

        public void removeLager(int lagerId) {
            lagerMap.remove(lagerId);
        }
    }


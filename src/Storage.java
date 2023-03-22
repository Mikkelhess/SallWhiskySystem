
    import java.util.HashMap;

    public class Storage {
        private HashMap<Integer, Lager> lagerMap = new HashMap<>();

        public Storage() {

        }

        public Lager createLager() {
            Lager lager = new Lager();
            lagerMap.put(lager.getLagerId(), lager);
            return lager;
        }

        public Lager getLager(int lagerId) {
            return lagerMap.get(lagerId);
        }

        public void removeLager(int lagerId) {
            lagerMap.remove(lagerId);
        }
    }


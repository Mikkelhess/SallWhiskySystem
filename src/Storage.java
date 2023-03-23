
    import java.util.HashMap;

    public class Storage {
        private static HashMap<Integer, Lager> lagerMap = new HashMap<>();
        public Lager createLager() {
            Lager lager = new Lager();
            addLager(lager);
            return lager;
        }

        public static void addLager(Lager lager) {
            lagerMap.put(lager.getLagerId(), lager);
        }


        public static Lager getLager(int lagerId) {
            return lagerMap.get(lagerId);
        }

        public static void removeLager(int lagerId) {
            lagerMap.remove(lagerId);
        }
    }


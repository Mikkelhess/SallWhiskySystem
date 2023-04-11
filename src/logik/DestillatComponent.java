package logik;

public interface DestillatComponent {

    double getLiter();
    void setLiter(double liter);
    void add(DestillatComponent component);
    void remove(DestillatComponent component);
    DestillatComponent getChild(int index);
    String getNewMakeNummer();
    double getAlkoholProcent();




}

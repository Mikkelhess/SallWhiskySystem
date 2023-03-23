package logik;

public class Fad {

    private double størrelse;
    private static int idCounter = 1;
    private int fadId;
    private FadType fadType;

    public Fad(double størrelse, FadType fadType) {
        this.fadType = fadType;
        this.størrelse = størrelse;
        fadId = idCounter;
        idCounter++;
    }

    public int getFadId() {
        return fadId;
    }

    public FadType getFadType() {
        return fadType;
    }
}

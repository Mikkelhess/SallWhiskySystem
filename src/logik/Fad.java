package logik;

public class Fad {

    private double fadLiter;

    private static int idCounter = 1;
    private final int fadId;
    private FadType fadType;

    public Fad(double fadLiter, FadType fadType) {
        this.fadType = fadType;
        this.fadLiter = fadLiter;
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

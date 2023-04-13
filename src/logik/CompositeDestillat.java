package logik;

import java.util.ArrayList;
import java.util.List;

public class CompositeDestillat implements DestillatComponent {


    private List<DestillatComponent> components = new ArrayList<>();
    private int leafCounter = 1;
    private static int newMakeCounter = 1;
    private String newMakeNummer;
    private double alkoholProcent;
    private double totalLiter;
    private double brugteLiter;


    public CompositeDestillat(double alkoholProcent) {
        this.alkoholProcent = alkoholProcent;

        this.brugteLiter = 0;

        this.newMakeNummer = String.valueOf(newMakeCounter);
        newMakeCounter++;


    }

    public String getNewMakeNummer() {
        return newMakeNummer;
    }

    public static int getNewMakeCounter() {
        return newMakeCounter;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }


    @Override
    public double getLiter() {
        double totalAmount = 0;
        for (DestillatComponent component : components) {
            totalAmount += component.getLiter();
        }
        return totalAmount;
    }
    @Override
    public void setLiter(double liter) {
        throw new UnsupportedOperationException("Cannot see amount directly on a composite");
    }

    public LeafDestillat createLeaf(double liter) {
        String leafMakeNummer = newMakeNummer + "." + leafCounter++;
        LeafDestillat leafDestillat = new LeafDestillat(liter, leafMakeNummer, getAlkoholProcent());
        components.add(leafDestillat);
        brugteLiter += liter;

        return leafDestillat;
    }

    public List<LeafDestillat> getLeaves() {
        List<LeafDestillat> leaves = new ArrayList<>();
        for (DestillatComponent component : components) {
            if (component instanceof LeafDestillat) {
                leaves.add((LeafDestillat) component);
            }
        }
        return leaves;
    }

    public void setTotalLiter(double totalLiter) {
        this.totalLiter = totalLiter;
    }

    public double getTotalLiter() {
        return totalLiter;
    }

    public void setBrugteLiter(double brugteLiter) {
        this.brugteLiter = brugteLiter;
    }

    public double getBrugteLiter() {
        return brugteLiter;
    }

    @Override
    public void add(DestillatComponent component) {
        components.add(component);
    }

    @Override
    public void remove(DestillatComponent component) {
        components.remove(component);
    }

    public List<DestillatComponent> getComponents() {
        return new ArrayList<>(components);
    }

    @Override
    public DestillatComponent getChild(int index) {
        return components.get(index);
    }

    @Override
    public String toString() {
        return "Destillat " + newMakeNummer + ": " + brugteLiter + " / " + totalLiter + " liter, Alkohol Procent: " + alkoholProcent;
    }
}

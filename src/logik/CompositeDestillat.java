package logik;

import java.util.ArrayList;
import java.util.List;

public class CompositeDestillat implements DestillatComponent {


    private List<DestillatComponent> components = new ArrayList<>();
    private double liter = getLiter();
    private int leafCounter = 1;
    private static int newMakeCounter = 1;
    private String newMakeNummer;
    private double alkoholProcent;
    private double totalCapacity;
    private double usedCapacity;


    public CompositeDestillat(double alkoholProcent) {
        this.alkoholProcent = alkoholProcent;

        this.newMakeNummer = String.valueOf(newMakeCounter);
        newMakeCounter++;

        this.totalCapacity = 0;
        this.usedCapacity = 0;
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

    public void setTotalCapacity(double totalCapacity) {
        this.totalCapacity = totalCapacity;
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

    public LeafDestillat createLeaf(double amount) {

        if (usedCapacity + amount > totalCapacity) {
            throw new IllegalStateException("Cannot add more leaves than the composite's total capacity.");
        }

        String leafMakeNummer = newMakeNummer + "." + leafCounter++;
        LeafDestillat leafDestillat = new LeafDestillat(amount, leafMakeNummer, getAlkoholProcent());
        components.add(leafDestillat);
        usedCapacity += amount;

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

}

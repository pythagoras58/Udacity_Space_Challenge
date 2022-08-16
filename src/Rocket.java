import java.util.ArrayList;

public class Rocket implements SpaceShip{
    private ArrayList<Item> items;
    private int cost;
    private double rocketWeight;
    private double maxWeight;
    private int id;

    public Rocket(int cost, double rocketWeight, double maxWeight) {
        items = new ArrayList<>();
        this.id = -1;
        this.cost = cost;
        this.rocketWeight = rocketWeight;
        this.maxWeight = maxWeight;
    }

    // getters


    public int getCost() {
        return cost;
    }

    public double getRocketWeight() {
        return rocketWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public int getId() {
        return id;
    }

    // implemented methods
    @Override
    public boolean launch() {
        return true;
    }

    @Override
    public boolean land() {
        return true;
    }

    // worked ons

    @Override
    public boolean canCarry(Item item) {
        return (getWeightOfCargo() + item.getWeigth()) <= (maxWeight - rocketWeight);
    }

    @Override
    public void Carry(Item item) {
        items.add(item);
    }

    // defined methods
    public double getWeightOfCargo(){
        double cargoWeight = 0;
        for(Item cargoItem : items){
            cargoWeight = cargoWeight + cargoItem.getWeigth();
        }
        return cargoWeight;
    }
}

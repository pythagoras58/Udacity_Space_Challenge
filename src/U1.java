public class U1 extends Rocket{

    private static int count = 0;
    private int id;

    public U1() {
        super(100,10000,18000);
        count++;
        this.id = id;
    }

    /**
     * Find out if the launch was successful
     * */

    @Override
    public boolean launch(){
        int random = (int) (Math.random() * 100) + 1;
        return random > (5.0 * getWeightOfCargo() / (getMaxWeight() - getRocketWeight()));
    }

    /**
     * Find out if the landing was successful
     * */

    @Override
    public boolean land() {
        int random = (int) (Math.random() * 100) + 1;
        return random > (1.0 * getWeightOfCargo() / (getMaxWeight() - getRocketWeight()));
    }

    @Override
    public int getId() {
        return id;
    }
}

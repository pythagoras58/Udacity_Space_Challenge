import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {

    /**
     *responsible for reading item data and filling up the rockets.
     * */

    //loadItems
    public ArrayList<Item> loadItems(String filename){
        ArrayList<Item> items = new ArrayList<>();

        File file = new File(filename);

        try {
            if (file.exists()){

                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    int index = line.indexOf("=");

                    String name = line.substring(0, index);

                    int weight = Integer.parseInt(line.substring(index + 1));

                    Item item = new Item(name, weight);

                    items.add(item);
                }

            }else {
                System.out.println("[ERROR: COULD NOT LOCATE THE FILE]");
            }

        }catch (Exception e){
            System.out.println("[ERROR: SORRY AN ERROR OCCURRED] ->" + e);
        }
        return items;
    }

    public ArrayList<Rocket> loadU1(ArrayList<Item> items){
        ArrayList<Rocket> rockets = new ArrayList<>();

        while (items.size() > 0){
            // instance of U1 class
            U1 u1 = new U1();

            if(Main.VERBOSE){
                System.out.println("[U1 ROCKET] -> " + u1.getId());
                System.out.println("==========================");
            }

            // load the items onto the cargo
            for(int i= items.size()-1; i >=0; i--){
                if(u1.canCarry(items.get(i))){
                    u1.Carry(items.get(i));
                    if (Main.VERBOSE){
                        System.out.println("[ITEM NAME]-> " + items.get(i).getName() + " [ITEM WEIGHT]->" + items.get(i).getWeigth());

                    }
                    items.remove(i);
                }
            }

            rockets.add(u1);

            if (Main.VERBOSE){
                System.out.println("[TOTAL CARGO WEIGHT]-> " + u1.getWeightOfCargo());
                System.out.println(" ");
            }
        }

        return rockets;
    }

    public ArrayList<Rocket> loadU2(ArrayList<Item> items){
        ArrayList<Rocket> rockets = new ArrayList<>();

        while (items.size() > 0){
            // instance of U1 class
            U2 u2 = new U2();

            if(Main.VERBOSE){
                System.out.println("[U1 ROCKET] -> " + u2.getId());
                System.out.println("==========================");
            }

            // load the items onto the cargo
            for(int i= items.size()-1; i >=0; i--){
                if(u2.canCarry(items.get(i))){
                    u2.Carry(items.get(i));
                    if (Main.VERBOSE){
                        System.out.println("[ITEM NAME]-> " + items.get(i).getName() + " [ITEM WEIGHT]->" + items.get(i).getWeigth());

                    }
                    items.remove(i);
                }
            }

            rockets.add(u2);

            if (Main.VERBOSE){
                System.out.println("[TOTAL CARGO WEIGHT]-> " + u2.getWeightOfCargo());
                System.out.println(" ");
            }
        }

        return rockets;
    }

    public int runSimulation(ArrayList<Rocket> rockets){
        int totalCost = 0;

        for(int i = rockets.size() -1; i>=0; i--){
            boolean launch;
            boolean land;

            do {
                totalCost = totalCost + rockets.get(i).getCost();
                launch = rockets.get(i).launch();
                land = rockets.get(i).land();

                if (Main.VERBOSE) {
                    if (!launch) {
                        System.out.println("  Rocket " + rockets.get(i).getId() + " exploded on launch! Will rebuild rocket and re-launch.");
                    } else {
                        System.out.print("  Rocket " + rockets.get(i).getId() + " successfully launched... ");
                        if (!land) {
                            System.out.println(" but crashed on landing! Will rebuild rocket and re-launch.");
                        } else {
                            System.out.println(" and landed on its mark!");
                        }
                    }

                }

            }while (!launch || !land);
            rockets.remove(i);

        }
        if (Main.VERBOSE) {
            System.out.println("");
        }

        return totalCost * 1000000;
    }
}

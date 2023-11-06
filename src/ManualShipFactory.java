import java.util.ArrayList;
import java.util.List;

public class ManualShipFactory extends ShipFactory {


    public ManualShipFactory(ArrayList<Ship>ships) {
       super(ships);
    }

    @Override
    protected Coord getLeadCoord(String shipName) {
        while (true){
            String start_coord = ConsoleHelper.getInput("Please input starting coordinate for your" + shipName + ": ");
            try{
                Coord startCoord = new Coord(start_coord);
                return startCoord;
            }catch(Exception e){
                System.out.println("Please enter valid coordinate. ");
            }
            
        }
    }

    @Override
    protected Direction getDirection(List<Direction> directions, String shipName) {
        System.out.println("Theses are the valid directions for your " + shipName + ": ");
        for(int i=0; i<directions.size();i++){
            System.out.println(i+1+") "+directions.get(i).toString());
        }
        while (true) {
            String direction_input=ConsoleHelper.getInput("Please pick a direction: ");
            try{
                int direction_int = Integer.parseInt(direction_input)-1;
                if(direction_int<0 || direction_int>directions.size()-1) {
                   throw new Exception();
                }
                return directions.get(direction_int);
            }catch(Exception e){
                System.out.println("Please enter valid number.");
            }

        }
    }
    
}

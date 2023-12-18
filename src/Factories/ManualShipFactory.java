package Factories;

import Common.*;
import java.util.List;

public class ManualShipFactory extends ShipFactory {

    public ManualShipFactory() {
        super();
    }

    @Override
    protected Coord getLeadCoord(String shipName) {
        while (true) {
            String value = ConsoleHelper.getInput("Please enter the starting position for your " + shipName + ": ");
            try {
                Coord leadCoord = new Coord(value);
                if(!totalShipCoords.contains(leadCoord)){
                    return leadCoord;
                }
            } catch (Exception e) {
                System.out.println("That's not a valid coordinate, please try again.");
            }
        }
    }

    @Override
    protected Direction getDirection(List<Direction> directions, String shipName) {
        System.out.println("Here are the valid directions for your " + shipName + ": \n");
        for (int i = 0; i < directions.size(); i++) {
            // Add one for user friendliness.
            System.out.println((i + 1) + ") " + directions.get(i).toString());
        }
        int directionChoice = ConsoleHelper.getInputBetween("", 0, directions.size()+1, "Not a valid placement option.")-1;//convert back to 0 index base.
        return directions.get(directionChoice);
    }
}

package Factories;
import java.util.List;
import java.util.Random;
import Common.Direction;
import Common.Ship;
import Common.Coord;

public class AutoShipFactory extends ShipFactory {

    public AutoShipFactory() {
        super();
        genPlacements();
    }

    protected void genPlacements() {
        for (Ship ship : this.ships) {
            placeAShip(ship);
        }
    }

    @Override
    protected Direction getDirection(List<Direction> directions, String shipName) {
        Random rand = new Random();
        return directions.get(rand.nextInt(directions.size()));
    }

    @Override
    protected Coord getLeadCoord(String shipName) {
        Random rand = new Random();
        while (true) {
            Coord coord = null;
            try {
                coord = new Coord(rand.nextInt(10), rand.nextInt(10));
                if(!totalShipCoords.contains(coord)){
                    return coord;
                }
            } catch (Exception e) {

            }
        }
    }
}

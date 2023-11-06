import java.util.List;
import java.util.Random;

public class AutoFactory extends ShipFactory{

    Random random = new Random();

    // Constructor: Takes a list of ships and places them
    public AutoFactory(List<Ship> ships){
        super(ships);
        randomPlacement();
    }

    // Places the ships at random coordinates
    protected void randomPlacement(){
        for (Ship ship : ships){
            placeAShip(ship);
        }
    }

    // Randomly generates a random row/column for a starting coord
    @Override
    protected Coord getLeadCoord(String shipName) {
        int randomX = random.nextInt(this.gridSize);
        int randomY = random.nextInt(this.gridSize);
        Coord randomCoord = new Coord(randomX,randomY);
        return randomCoord;
    }

    // Randomly generates a random direction for the ship to face
    @Override
    protected Direction getDirection(List<Direction> directions, String shipName) {
        int randomDirIndex = random.nextInt(directions.size());
        return directions.get(randomDirIndex);
    }
}

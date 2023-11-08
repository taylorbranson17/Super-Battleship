import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// This class is abstract! Can no longer be instantiated! 
public abstract class ShipFactory{

    // Variables to store the ship info
    protected List<Ship> ships; 
    protected String[] shipNames = {"Carrier","Battleship","Patrol Boat","Submarine","Destroyer"};
    protected List<Coord> totalShipCoords = new ArrayList<Coord>();

    protected int gridSize = 9;

    // Must override!
    protected abstract Coord getLeadCoord(String shipName);  //Gets/Generates a lead Coord
    protected abstract Direction getDirection(List<Direction> directions, String shipName); // Gets/Generates a direction

    // Constructor
    public ShipFactory(List<Ship> ships){
        this.ships = ships;
        genShips();
    }

    // Places a ship!
    public void placeAShip(Ship ship){
        Coord leadCoord = getLeadCoord(ship.getName());
        List<Direction> validDirections = filterOverlap(leadCoord, ship.getLength());
        Direction direction = getDirection(validDirections, ship.getName()); // Will be random in Automatic and user-entered in Manual
        List<Coord> shipCoords = genShipCoords(leadCoord, ship.getLength(), direction);
        ship.setCoords(shipCoords); 
        totalShipCoords.addAll(shipCoords);
    }

    // Returns a list of valid directions given the ship's leading coordinate (leadCoord) and its length
    public List<Direction> filterBoardBounds(Coord leadCoord, int length){

        // Unpack the coordinate's row/column
        int row = leadCoord.getY();
        int column = leadCoord.getX();

        // Every direction!
        List<Direction> validDirectionList = new ArrayList<Direction>(Arrays.asList(Direction.values()));

        // Check North
        if (row - length + 1 < 0){
            validDirectionList.remove(Direction.N);
        }
        
        // Check South
        if (row + length - 1 > 9){
            validDirectionList.remove(Direction.S);
        }

        // Check East
        if (column + length - 1 > 9){
            validDirectionList.remove(Direction.E);
        }

        // Check West
        if (column - length + 1 < 0){
            validDirectionList.remove(Direction.W);
        }

        return validDirectionList;
    }
    
    // Generate ships from an array of names
    // TODO: This is supposed to generate a list of ships with no coords, right?
    protected void genShips(){
        for (String name : shipNames){
            ships.add(new Ship(name));
        }
    }

    // Generates a list of valid coordinates for a ship of specified 
    protected ArrayList<Coord> genShipCoords(Coord leadCoord, int length, Direction direction){ 
        // Create a list to store our coordinates in
        ArrayList<Coord> shipCoords = new ArrayList<Coord>();

        // Iterate through the length of the ship and add a new coordinate for each space
        for (int l = 0; l < length; l++){
            int row = leadCoord.getY();
            int column = leadCoord.getX();
            switch (direction) {
                case N: 
                    row -= l;
                    break;
                case E: 
                    column += l;
                    break;
                case S: 
                    row += l;
                    break;
                case W: 
                    column -= l;
                    break;
            }
            shipCoords.add(new Coord(column,row));
        }

        return shipCoords;
    }

    public List<Direction> filterOverlap(Coord leadCoord, int length){

        //filters valid directions by leadCoord's position on board.
        List<Direction> validDirections = filterBoardBounds(leadCoord, length);
        List<Direction> invalidDirections = new ArrayList<Direction>();
        //loops through directions, generating coords for the ship.
        //if totalShipCoords contains any of them, it removes the directions from valid list.
        for (Direction validDirection : validDirections){
            List<Coord> dirCoords = genShipCoords(leadCoord, length, validDirection);
            for (Coord coord : dirCoords){
                if (this.totalShipCoords.contains(coord)){
                    invalidDirections.add(validDirection);
                }
            }
        }

        validDirections.removeAll(invalidDirections);
        return validDirections;
    }
}
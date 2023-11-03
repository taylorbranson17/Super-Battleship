import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ShipFactory{

    // Variables to store the ship info
    protected List<Ship> ships; 
    protected String[] shipNames = {"carrier","battleship","cruiser","submarine","destroyer"};
    protected List<Coord> totalShipCoords = new ArrayList<Coord>();

    protected int gridSize = 9;

    // Must override!
    protected abstract Coord getLeadCoord();  //Gets/Generates a lead Coord
    protected abstract Direction getDirection(List<Direction> directions); // Gets/Generates a direction

    // Constructor
    public ShipFactory(ArrayList<Ship> ships){
        this.ships = ships;
        genShips();
    }

    // Places a ship!
    public void placeAShip(Ship ship){
        Coord leadCoord = getLeadCoord();
        List<Direction> validDirections = filterOverlap(leadCoord, ship.getLength());
        Direction direction = getDirection(validDirections); // Will be random in Automatic and user-entered in Manual
        List<Coord> shipCoords = genShipCoords(leadCoord, ship.getLength(), direction);
        ship.setCoords(shipCoords); 
        totalShipCoords.addAll(shipCoords);
    }

    // Returns whether the leading row/column are within bounds
    protected boolean validateLeadCoord(Coord coord){
        int row = coord.getY();
        int column = coord.getX();

        if (row > this.gridSize || row < 0 || column > this.gridSize || column < 0){
            return false;
        } else {
            return true;
        }
    }

    // Returns a list of valid directions given the ship's leading coordinate (leadCoord) and its length
    protected List<Direction> filterDirections(Coord leadCoord, int length){

        // Unpack the coordinate's row/column
        int row = leadCoord.getY();
        int column = leadCoord.getX();

        // Every direction!
        List<Direction> validDirectionList = new ArrayList<Direction>(Arrays.asList(Direction.values()));

        // Check North
        if (row - (length + 1) < 0){
            validDirectionList.remove(Direction.N);
        }
        
        // Check South
        if (row + (length - 1) > 9){
            validDirectionList.remove(Direction.S);
        }

        // Check East
        if (column + (length - 1) > 9){
            validDirectionList.remove(Direction.E);
        }

        // Check West
        if (column - (length + 1) < 0){
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

        // Unpack the leadCoord's row/column
        int startRow = leadCoord.getY();
        int startColumn = leadCoord.getX();

        int row = startRow;
        int column = startColumn;

        // Create a list to store our coordinates in
        ArrayList<Coord> shipCoords = new ArrayList<Coord>();

        // Iterate through the length of the ship and add a new coordinate for each space
        for (int l = 0; l < length; l++){
            switch (direction) {
                case N: 
                    row = startRow-l;
                    break;
                case E: 
                    column = startColumn+l;
                    break;
                case S: 
                    row = startRow+l;
                    break;
                case W: 
                    column = startColumn-l;
                    break;
            }
            shipCoords.add(new Coord(column,row));
        }

        return shipCoords;
    }

    // Returns true if no coordinates overlap between the two lists
    protected List<Direction> filterOverlap(Coord leadCoord, int length){

        List<Direction> validDirections = filterDirections(leadCoord, length);
        List<Direction> invalidDirections = new ArrayList<Direction>();

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
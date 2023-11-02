import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AutoFactory extends ShipFactory{
    
    // Returns a randomized list of ships, complete with coordinates
    public List<Ship> randomPlacement() throws Exception{

        // Create the ships
        List<Ship> autoShips = super.genShips(this.shipNames);

        // Iterate through each ship
        for (Ship ship : autoShips){

            // Create a horrifying time loop where the ship must be placed in a valid location lest it experience rebirth
            while (true){

            // Generate a random leading coordinate for the ship
            Coord randomLeadCoord = genRandomCoord();

            // Validate that the random leading coordinate is within bounds
            if (!super.validateLeadCoord(randomLeadCoord)){
                // Restart the loop if it is not
                continue;
            }

            // Make sure the random leading coordinate does not overlap any other ships
            if (!super.filterOverlap(randomLeadCoord, this.totalShipCoords)){
                continue;
            }

            // Filter possible directions for the leading coordinate
            List<Direction> possibleDirections = super.filterDirections(randomLeadCoord, ship.getLength());

            // Choose one of those directions at random
            int randomDirectionIndex = ThreadLocalRandom.current().nextInt(0,possibleDirections.size());
            Direction randomDirection = possibleDirections.get(randomDirectionIndex);

            // Generate the rest of the coordinates for that ship 
            ArrayList<Coord> theseCoords = super.genShipCoords(randomLeadCoord,ship.getLength(),randomDirection);

            // Ensure those coordinates do not override another ship
            if (!super.filterOverlap(theseCoords,this.totalShipCoords)){
                continue;
            }

            // Add these new coordinates to the total ship coords
            for (Coord thisCoord : theseCoords){
                this.totalShipCoords.add(thisCoord);
            }

            // Give the ship its new data
            ship.setCoords(theseCoords);
            break;
            }
        }
        return autoShips;
    }

    // Generate a single random coordinate
    public Coord genRandomCoord(){
        int randomX = ThreadLocalRandom.current().nextInt(0,this.gridSize+1);
        int randomY = ThreadLocalRandom.current().nextInt(0,this.gridSize+1);
        Coord randomCoord = new Coord(randomX,randomY);
        return randomCoord;
    }
}

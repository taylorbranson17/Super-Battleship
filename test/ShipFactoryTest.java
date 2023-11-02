import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

// This might count as another task, but it is so important that I figured I couldn't go without it.

public class ShipFactoryTest {
    
    ShipFactory shipFactory;

    @Before
    public void init(){
        shipFactory = new ShipFactory();
    }

    @Test
    public void testValidateLeadCoords(){
        // Check Valid X/Y
        assertTrue(shipFactory.validateLeadCoord(new Coord(3,3)));
        // Check Invalid X/Valid Y
        assertFalse(shipFactory.validateLeadCoord(new Coord(20,3)));
        // Check Valid X, invalid Y
        assertFalse(shipFactory.validateLeadCoord(new Coord(3,20)));
        // Check Invalid X and Y
        assertFalse(shipFactory.validateLeadCoord(new Coord(20,20)));
    }

    @Test
    public void testFilterDirections(){

        // ORDER: [N, E, S, W]

        // Test valid directions at 0,0 (East and South)
        assertTrue(shipFactory.filterDirections(new Coord(0,0),2).toString().equals("[E, S]"));
        // Test valid directions at 4,0 (East, South, West)
        assertTrue(shipFactory.filterDirections(new Coord(4,0),2).toString().equals("[E, S, W]"));
        // Test valid directions at 9,0 (South, West)
        assertTrue(shipFactory.filterDirections(new Coord(9,0),2).toString().equals("[S, W]"));
        // Test valid directions at 0,4 (North, East, South)
        assertTrue(shipFactory.filterDirections(new Coord(0,4),2).toString().equals("[N, E, S]"));
        // Test valid directions at 4,4 (NorthFalseections(new Coord(4,4),2).toString().equals("[N, E, S, W]"));
        // Test valid directions at 9,0 (North, South, West)
        assertTrue(shipFactory.filterDirections(new Coord(9,4),2).toString().equals("[N, S, W]"));
        // Test valid directions at 0,9 (North, East)
        assertTrue(shipFactory.filterDirections(new Coord(0,9),2).toString().equals("[N, E]"));
        // Test valid directions at 4,9 (North, East, West)
        assertTrue(shipFactory.filterDirections(new Coord(4,9),2).toString().equals("[N, E, W]"));
        // Test valid directions at 9,9 (North, West)
        assertTrue(shipFactory.filterDirections(new Coord(9,9),2).toString().equals("[N, W]"));
    }

    @Test
    public void testGenShips(){

        String[] shipNames = shipFactory.getShipNames();
        List<Ship> shipList = shipFactory.genShips(shipNames);

        // Verify that the names of each ship match up with the names in the shipNames array
        for (int s = 0; s < shipList.size(); s++){
            assertTrue(shipList.get(s).getName().equals(shipNames[s]));
        }
    }

    @Test
    public void testGenShipCoords() throws Exception{

        List<Coord> coordList = shipFactory.genShipCoords(new Coord(6,2), 4, Direction.S);
        System.out.print(coordList);

        // Not me testing with .toString() because I don't remember how to make a list of four coordinates
        assertTrue(coordList.toString().equals("[C7, D7, E7, F7]"));
    }

    @Test
    public void testFilterOverlap() throws Exception{
        // Test two lists of coords that don't intersect
        List<Coord> validShip1 = shipFactory.genShipCoords(new Coord("F5"), 3, Direction.N);
        List<Coord> validShip2 = shipFactory.genShipCoords(new Coord("D6"), 3, Direction.S);
        assertTrue(shipFactory.filterOverlap(validShip1,validShip2));

        // Test two lists of coords that do intersect
        List<Coord> invalidShip1 = shipFactory.genShipCoords(new Coord("A3"), 4, Direction.S);
        List<Coord> invalidShip2 = shipFactory.genShipCoords(new Coord("B2"), 4, Direction.E);
        assertFalse(shipFactory.filterOverlap(invalidShip1,invalidShip2));

        // Test a single coord not intersecting a list
        Coord validCoord = new Coord("C10");
        List<Coord> validCoordShip = shipFactory.genShipCoords(new Coord("B9"), 3, Direction.S);
        assertTrue(shipFactory.filterOverlap(validCoord,validCoordShip));

        //Test a single coord intersecting a list
        Coord invalidCoord = new Coord("G10");
        List<Coord> invalidCoordShip = shipFactory.genShipCoords(new Coord("G10"), 3, Direction.W);
        assertFalse(shipFactory.filterOverlap(invalidCoord,invalidCoordShip));

    }

}
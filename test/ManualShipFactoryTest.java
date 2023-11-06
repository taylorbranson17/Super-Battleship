import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ManualShipFactoryTest {
    ArrayList<Ship> ships = new ArrayList<>();
    ManualShipFactory fact;

    @Before
    public void init(){
        fact = new ManualShipFactory(ships);
    }


  /*   @Test
    public void testGetLeadCoord() {
        ArrayList<Ship> ships = new ArrayList<>();
        ManualShipFactory factory = new ManualShipFactory(ships);

        ConsoleHelper.getInput("A1"); 
        Coord leadCoord = factory.getLeadCoord();
        assertEquals(new Coord("A1"), leadCoord);
    }

    @Test
    public void testGetDirection() {
        ArrayList<Ship> ships = new ArrayList<>();
        ManualShipFactory factory = new ManualShipFactory(ships);

     
        List<Direction> directions = List.of(Direction.N, Direction.E, Direction.S, Direction.W);

        ConsoleHelper.getInput("3"); 
        Direction selectedDirection = factory.getDirection(directions);
        assertEquals(Direction.S, selectedDirection);
    } */

    @Test
    public void TestFilterDirection(){
        List<Direction> directions = fact.filterOverlap(new Coord(5,5), 5);
        assertEquals(Arrays.asList(Direction.values()), directions );
    }
}

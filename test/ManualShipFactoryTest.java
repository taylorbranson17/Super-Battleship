import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ManualShipFactoryTest {

    @Test
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
    }
}

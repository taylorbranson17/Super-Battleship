import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

// This might count as another task, but it is so important that I figured I couldn't go without it.

public class AutoFactoryTest {
    
    AutoFactory autoFactory;

    @Before
    public void init(){
        autoFactory = new AutoFactory();
    }

    @Test
    public void testRandomPlacement() throws Exception{
        List<Ship> shipList = autoFactory.randomPlacement();

        System.out.print(shipList + "\n");

        assertTrue(shipList.size() == 5);

        for (Ship ship : shipList){
            System.out.print(ship.getName() + ": " + ship.getCoords() + "; \n");
        }
    }


}

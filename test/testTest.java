import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class testTest {
    
    @Test //testing that tests actually work lol
    public void TestTest(){
        Coord testCoord = new Coord(0,0);
        Coord testCoord2 = new Coord(0,0);
        assertTrue(testCoord.equals(testCoord2));
    }

}

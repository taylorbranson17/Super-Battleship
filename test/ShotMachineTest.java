import static org.junit.Assert.*;
import org.junit.Test;

public class ShotMachineTest {
    ShotMachine shMach = new ShotMachine();

    //Test that it actually returns a shot Coord...lol...
    @Test
    public void TestShotReturn(){
        assertTrue(shMach.getShot() instanceof Coord);
        assertTrue(shMach.getState().equals(AIMode.HUNT));
    }
    //Test receiving hit in HUNT mode state should become "BRACKET"
    @Test
    public void TestHitInHuntReturn() throws Exception{
        ShotResult result = ShotResult.HIT;
        Coord shotCoord = new Coord(4,5);
        result.setCoord(shotCoord);
        shMach.receiveResult(result);
        assertTrue(shMach.getFirstHit().equals(shotCoord));
        assertTrue(shMach.getState().equals(AIMode.BRACKET));
    }
    //Test receiving hit in BRACKET mode state should become "PURSUIT"
    @Test
    public void TestHitInBracketReturn() throws Exception{
        assertTrue(shMach.getState().equals(AIMode.HUNT));
        ShotResult result = ShotResult.HIT;
        Coord shotCoord = new Coord(4,5);
        result.setCoord(shotCoord);
        shMach.receiveResult(result);
        assertTrue(shMach.getLastHit().equals(shotCoord));
        assertTrue(shMach.getState().equals(AIMode.PURSUIT));
    }
}

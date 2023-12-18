import static org.junit.Assert.*;
import org.junit.Test;


public class EnumModeMapTest {
    //******Tests core functions. It would take too much time to test all 16 possibilities, so I'm happy with these...********/

    //Returns 'BRACKET', when Mode is HUNT and event is HIT.
    @Test
    public void TestHuntHit(){
        assertTrue(AIMode.BRACKET.equals(EventModeMap.getMode(AIMode.HUNT, Event.HIT)));
    }

    //Should return 'PURSUIT' when BRACKETING and receive a HIT.
    @Test
    public void TestBracketHit(){
        assertTrue(AIMode.PURSUIT.equals(EventModeMap.getMode(AIMode.BRACKET, Event.HIT)));
    }

    //Should return 'REVPURSUIT' when PURSUING and receive a MISS.
    @Test
    public void TestPursuitMiss(){
        assertTrue(AIMode.REVPURSUIT.equals(EventModeMap.getMode(AIMode.PURSUIT, Event.MISS)));
    }

    //Should return 'BRACKET' when REVPURSUING and receive a MISS.
    @Test
    public void TestRevPursuitMiss(){
        assertTrue(AIMode.BRACKET.equals(EventModeMap.getMode(AIMode.REVPURSUIT, Event.MISS)));
    }

    //Should return 'REVPURSUIT' when PURSUING and receive a OUT.
    @Test
    public void TestPursuitOut(){
        assertTrue(AIMode.REVPURSUIT.equals(EventModeMap.getMode(AIMode.PURSUIT, Event.OUT)));
    }

    //Should return 'BRACKET' when REVPURSUING and receive a OUT.
    @Test
    public void TestRevPursuitOut(){
        assertTrue(AIMode.BRACKET.equals(EventModeMap.getMode(AIMode.REVPURSUIT, Event.OUT)));
    }

    //Should return 'HUNT' when BRACKETING and receive a OUT.
    @Test
    public void TestBracketOut(){
        assertTrue(AIMode.HUNT.equals(EventModeMap.getMode(AIMode.BRACKET, Event.OUT)));
    }
}

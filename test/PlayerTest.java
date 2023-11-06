/* import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    Player player;

    @Before
    public void init(){
        player = new Player("Player 1");
    }

    //Had to comment out player config to test for equality. Reenstantiated it for use after.
    @Test
    public void testReturnOfName(){
        assertTrue(player.getName().equals("Player 1"));
    } 

    //Ran Print test to verify Player config asks for ship placement decision after player initialization.
    public void noTest(){
        Player player = new Player("Player 1");
    }

    //Ran Print test to verify player prints boards...printBoard() made public for testing only.
    public void noTest2(){
        player.printBoard();
    }

    //Test to verify player receives shot, and returns correct result. With no placed ships, result should be a miss...
    @Test
    public void TestReceiveShot(){
        Coord shotCoord = new Coord(4,5);
        ShotResult result = player.receiveShot(shotCoord);
        assertEquals(result.getCoord(), shotCoord);
        assertEquals(ShotResult.MISS, result);
    }

    //Test to verify that a player's turn can be validated by the amount of ships sunk. Since the length of 'ships' is 0,
    // test should come back positive from OceanGrid (all ships are 'sunk'), inverted within the player class, and produce 'FALSE'- their turn cannot commence.
    @Test
    public void TestAllShipsSunk(){
        assertFalse(player.validateTurn());
    }
}
 */
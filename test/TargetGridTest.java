import static org.junit.Assert.*;
import org.junit.Test;


//A test for the specialty functions within a target Grid. Doesn't contain general grid testing, besides Cell initialization.
public class TargetGridTest {
    TargetGrid tGrid = new TargetGrid();

    // Test cell generation for each position on board.
    @Test
    public void TestTotalGridSpaces() {
        int cellCount = 0;
        for (int i = 0; i < 10; i++) {
            for (int y = 0; y < 10; y++) {
                if (tGrid.getCellatXY(i, y) instanceof Cell) {
                    cellCount++;
                }
            }
        }
        assertEquals(100, cellCount);
    }

    // No previous shots taken, verify that a shot comes back TRUE.
    @Test
    public void TestIsValidShotTrue() {
        Coord shotCoord = new Coord(3, 2);
        assertTrue(tGrid.isValidShot(shotCoord));
    }

    //A test to identify the marking of the target grid based on a shot result. 
    @Test
    public void TestShotRegister(){
        ShotResult result = ShotResult.HIT;
        result.setCoord(new Coord(3,3));
        ShotResult result2 = ShotResult.MISS;
        result2.setCoord(new Coord(3,4));
        //Receive & print a HIT...
        tGrid.receiveShotResult(result);
        System.out.println(tGrid.print());
        //Then receive & print a MISS...
        tGrid.receiveShotResult(result2);
        System.out.println(tGrid.print());
        //also validate that all 'empty' cells are represented differently...
    }

    // Take two shots at same position, second one should come back FALSE.
    @Test
    public void TestIsValidShotFalse() {
        //Craft shotResult to mark first shot as a shot 'taken'...
        ShotResult result = ShotResult.MISS;
        result.setCoord(new Coord(3,2));
        tGrid.receiveShotResult(result);
        //Craft new shot on same coordinate...
        Coord shotCoord2 = new Coord(3, 2);
        assertFalse(tGrid.isValidShot(shotCoord2));
    }
}

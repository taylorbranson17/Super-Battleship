/* import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class OceanGridTest {
    OceanGrid oGrid = new OceanGrid();
    
    //Test that practice ship is added to 'Ships'.
    @Test
    public void TestPlacingOfShip(){
        Ship ship = new Ship("Submarine");
        ArrayList<Coord> shipCoords = new ArrayList<Coord>(Arrays.asList(new Coord(3,2), new Coord(3,3), new Coord(3,4)));
        ship.setCoords(shipCoords);
        oGrid.testPlaceShip(ship);
        assertEquals(1, oGrid.getShips());
    }

    //Test that when fired upon, test ship 'cells' return HIT...then sunk on the final.
    @Test
    public void TestShotOnShip(){
        Ship ship = new Ship("Submarine");
        ArrayList<Coord> shipCoords = new ArrayList<Coord>(Arrays.asList(new Coord(3,2), new Coord(3,3), new Coord(3,4)));
        ship.setCoords(shipCoords);
        oGrid.testPlaceShip(ship);
        assertEquals(ShotResult.HIT, oGrid.receiveShot(new Coord(3,2)));
        assertEquals(ShotResult.HIT, oGrid.receiveShot(new Coord(3,3)));
        assertEquals(ShotResult.SUNK, oGrid.receiveShot(new Coord(3,4)));
    }

    //Test that a random shot (where no ship should be), returns MISS...
    @Test
    public void TestBogusShot(){
        ShotResult result = oGrid.receiveShot(new Coord(8,2));
        assertEquals(ShotResult.MISS, result);
        //...and shotResult coord equals original shot...
        assertEquals(result.getCoord(), new Coord(8,2));
    }

    //Test ship is 'sunk', and OceanGrid returns TRUE- all ships are sunk.
    @Test
    public void TestCumulativeSunkShips(){
        TestShotOnShip();
        assertTrue(oGrid.allShipsSunk());
        assertEquals(oGrid.getShips(), 1);
    }

    //Test opposite of previous- no shots taken, all ships sunk returns FALSE.
    @Test
    public void TestCumulativeUnsunkShip(){
        TestPlacingOfShip();
        assertFalse(oGrid.allShipsSunk());
        //make sure the ship still exists.
        assertEquals(oGrid.getShips(), 1);
    }

    //Print test ran to make sure board prints in required format.
    @Test
    public void TestPrintingOfBoard(){
        System.out.println(oGrid.print());
    }

    //Print test ran to make sure board prints in required format with ship.
    @Test
    public void TestPrintingOfBoardAfterShip(){
        TestPlacingOfShip();
        System.out.println(oGrid.print());
    }

    //Print test ran to make sure board prints in required format with ship hit.
    @Test
    public void TestPrintingOfBoardAfterShipHit(){
        TestPlacingOfShip();
        oGrid.receiveShot(new Coord(3,2));
        System.out.println(oGrid.print());
    }

    //Print test ran to make sure board prints in required format with ship hit and random miss.
    @Test
    public void TestPrintingOfBoardAfterShipHitMiss(){
        TestPlacingOfShip();
        ShotResult result = oGrid.receiveShot(new Coord(3,2));
        ShotResult result2 = oGrid.receiveShot(new Coord(2,2));
        System.out.println(oGrid.print());
        assertEquals(ShotResult.MISS, result2);
        assertEquals(ShotResult.HIT, result);
    }

    @Test
    public void TestTotalGridSpaces(){
        int cellCount = 0;
        for(int i = 0; i < 10; i++){
            for(int y = 0; y < 10; y++){
                if(oGrid.getCellatXY(i, y) instanceof Cell){
                    cellCount++;
                }
            }
        }
        assertEquals(100, cellCount);
    }
}
 */
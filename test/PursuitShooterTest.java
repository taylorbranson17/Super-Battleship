import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PursuitShooterTest {
    PursuitShooter pShoot;

    @Before
    public void init() {
        try {
            pShoot = new PursuitShooter(Direction.N, new Coord(4, 5));
        } catch (Exception e) {

        }

    }

    // make sure it's generating it's coords...
    // Three coords, because the initial hit HUNT, BRACKET would have hit the
    // second, and at most, that leaves three coords in pursuit direction.
    @Test
    public void TestShotCoords() {
        ArrayList<Coord> shotCoords = pShoot.getCoords();
        assertTrue(shotCoords.size() == 3);
    }


    /**********************Test Shot Coord Sequence ***************/
    // Testing to make sure the Coords are generated in the right sequence...
    @Test
    public void TestShotCoordSequence() throws Exception {
        ArrayList<Coord> shotCoords = pShoot.getCoords();
        assertTrue(shotCoords.get(0).equals(new Coord(4, 4)));
        assertTrue(shotCoords.get(1).equals(new Coord(4, 3)));
        assertTrue(shotCoords.get(2).equals(new Coord(4, 2)));
    }

    // Reassign pShoot to the South, test Coord sequence.
    @Test
    public void TestShotCoordSequenceSouthShooter() throws Exception {
        pShoot = new PursuitShooter(Direction.S, new Coord(4, 5));
        ArrayList<Coord> shotCoords = pShoot.getCoords();
        assertTrue(shotCoords.get(0).equals(new Coord(4, 6)));
        assertTrue(shotCoords.get(1).equals(new Coord(4, 7)));
        assertTrue(shotCoords.get(2).equals(new Coord(4, 8)));
    }

    // Reassign pShoot to the West, test Coord sequence.
    @Test
    public void TestShotCoordSequenceWestShooter() throws Exception {
        pShoot = new PursuitShooter(Direction.W, new Coord(4, 5));
        ArrayList<Coord> shotCoords = pShoot.getCoords();
        assertTrue(shotCoords.get(0).equals(new Coord(3, 5)));
        assertTrue(shotCoords.get(1).equals(new Coord(2, 5)));
        assertTrue(shotCoords.get(2).equals(new Coord(1, 5)));
    }

    // Reassign pShoot to the East, test Coord sequence.
    @Test
    public void TestShotCoordSequenceEastShooter() throws Exception {
        pShoot = new PursuitShooter(Direction.E, new Coord(4, 5));
        ArrayList<Coord> shotCoords = pShoot.getCoords();
        assertTrue(shotCoords.get(0).equals(new Coord(5, 5)));
        assertTrue(shotCoords.get(1).equals(new Coord(6, 5)));
        assertTrue(shotCoords.get(2).equals(new Coord(7, 5)));
    }


    /***************************Test Shot Generation Near Bounds ************************/
    // Reassign pShoot near the top, test shot generation.
    @Test
    public void TestShotNearBoundsNorthCoordSequence() throws Exception {
        pShoot = new PursuitShooter(Direction.N, new Coord(4, 1));
        ArrayList<Coord> shotCoords = pShoot.getCoords();
        assertTrue(shotCoords.size() == 1);
        assertTrue(shotCoords.get(0).equals(new Coord(4,0)));
    }

    // Reassign pShoot near the West bound, test shot generation.
    @Test
    public void TestShotNearBoundsWestCoordSequence() throws Exception {
        pShoot = new PursuitShooter(Direction.W, new Coord(1, 1));
        ArrayList<Coord> shotCoords = pShoot.getCoords();
        assertTrue(shotCoords.size() == 1);
        assertTrue(shotCoords.get(0).equals(new Coord(0,1)));
    }

    // Reassign pShoot near the East bound, test shot generation.
    @Test
    public void TestShotNearBoundsEastCoordSequence() throws Exception {
        pShoot = new PursuitShooter(Direction.E, new Coord(9, 1));
        ArrayList<Coord> shotCoords = pShoot.getCoords();
        assertTrue(shotCoords.size() == 0);
    }

    // Reassign pShoot near the South bound, test shot generation.
    @Test
    public void TestShotNearBoundsSouthCoordSequence() throws Exception {
        pShoot = new PursuitShooter(Direction.S, new Coord(9, 9));
        ArrayList<Coord> shotCoords = pShoot.getCoords();
        assertTrue(shotCoords.size() == 0);
    }

    /*****************************Test Out of Shots ********************/
    //Start pursuit at top of board, heading north. Should throw INDEXOUTOFBOUNDS. Second shot should trigger Exception.
    @Test (expected = IndexOutOfBoundsException.class)
    public void TestRunThroughShots() throws Exception{
        pShoot = new PursuitShooter(Direction.N, new Coord(4,1));
        pShoot.getShot();
        pShoot.getShot();
    }
}

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class BracketShooterTest {
    BracketShooter bShoot;

    @Before
    public void init() throws Exception {
        bShoot = new BracketShooter(new Coord(4, 5));
    }

    // Test that all directions are being generated...
    @Test
    public void TestBracketShotGeneration() {
        ArrayList<Coord> shotCoords = bShoot.getCoords();
        assertEquals(4, shotCoords.size());
    }

    // Reassign bShoot on boundary, make sure it only generates 3 Coordinates.
    @Test
    public void TestNearBoundsShotGeneration() throws Exception {
        bShoot = new BracketShooter(new Coord(4, 0));
        assertEquals(3, bShoot.getCoords().size());
    }

    // Reassign bShoot on boundary, make sure it only generates 3 Coordinates. Test
    // them to be right.
    @Test
    public void TestNearBoundsShotCoords() throws Exception {
        bShoot = new BracketShooter(new Coord(4, 0)); // North is invalid...
        assertTrue(bShoot.getCoords().contains(new Coord(3, 0))); // Coord to the West.
        assertTrue(bShoot.getCoords().contains(new Coord(4, 1))); // Coord to the South.
        assertTrue(bShoot.getCoords().contains(new Coord(5, 0))); // Cood to the East.
    }

    // Reassign bShoot on center, make sure it generates all 4 Coordinates. Test
    // them to be right.
    @Test
    public void TestNearCenterShotCoords() throws Exception {
        assertTrue(bShoot.getCoords().contains(new Coord(5, 5))); // Coord to the East.
        assertTrue(bShoot.getCoords().contains(new Coord(4, 4))); // Coord to the North.
        assertTrue(bShoot.getCoords().contains(new Coord(4, 6))); // Cood to the South.
        assertTrue(bShoot.getCoords().contains(new Coord(3, 5))); // Cood to the West.
    }

    //Reassign bShoot to corner. Only valid directions are E & S, so should only generate 2 possible shots.
    @Test
    public void TestCornerShotCoords() throws Exception {
        bShoot = new BracketShooter(new Coord(0, 0));
        assertEquals(bShoot.getCoords().size(), 2);
        assertTrue(bShoot.getCoords().contains(new Coord(1, 0)));
        assertTrue(bShoot.getCoords().contains(new Coord(0, 1)));
    }

    // Reassign bShoot in corner. Take
    // three shots, third should throw INDEXOUTOFBOUNDS to be caught, and marked as an OUT, then shooter tranistioned within machine...
    @Test (expected = IndexOutOfBoundsException.class)
    public void TestOutOfShots() throws Exception {
        bShoot = new BracketShooter(new Coord(0, 0));
        bShoot.getShot();
        bShoot.getShot();
        bShoot.getShot();
    }

}

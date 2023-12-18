import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class HuntShooterTest {
    HuntShooter hShoot;

    @Before
    public void init(){
        //hShoot = new HuntShooter(2);
        //Changed ship lengths, run tests again...
        hShoot = new HuntShooter(3);
    }

    @Test
    public void TestSizeOfShooterShotList(){
        assertTrue(hShoot.getCoords().size() > 0);
    }

    //Ran into problems finding coord in shotCoords, so ran test to make sure 'shot' is actually a Coord... 
    @Test
    public void TestHuntShooterCoords(){
        Coord shot = hShoot.getShot();
        assertTrue(shot instanceof Coord);
    }

    //Create copy of unmutated list, and check for existence of 'shot'.
    @Test
    public void TestShotCoordPresence(){
        ArrayList<Coord> huntCoords = new ArrayList<Coord>();
        huntCoords.addAll(hShoot.getCoords());
        Coord shot = hShoot.getShot();
        assertTrue(huntCoords.contains(shot));
    }

    //shotCoords is a reference to HuntShooters coords. When .getShot() is run, it pulls it from referenced list, so it no longer contains it.
    @Test
    public void TestLackOfShotPresence(){
        ArrayList<Coord> shotCoords = hShoot.getCoords();
        Coord shot = hShoot.getShot();
        Coord shot2 = hShoot.getShot();
        Coord shot3 = hShoot.getShot();
        assertTrue(!shotCoords.contains(shot));
        assertTrue(!shotCoords.contains(shot2));
        assertTrue(!shotCoords.contains(shot3));
    }

    //Tests that shot coordinates land on the board.
    @Test
    public void TestViableShot(){
        Coord shot = hShoot.getShot();
        assertTrue(shot.getX() < 10 && shot.getX() > 0);
        assertTrue(shot.getY() < 10 && shot.getY() > 0);
    }
}


import org.junit.Test;

import Common.Constants;
import Common.ShotResult;

public class ConstantTests {
    
    //Print tests- mainly for ascii graphics...
    @Test
    public void TestMISSGraphic(){
        System.out.println(Constants.getASCII(ShotResult.MISS));
    }

    @Test
    public void TestHITGraphic(){
        System.out.println(Constants.getASCII(ShotResult.HIT));
    }

    @Test
    public void TestSUNKGraphic(){
        System.out.println(Constants.getASCII(ShotResult.SUNK));
    }

    @Test
    public void TestBATTLESHIPGraphic(){
        System.out.println(Constants.asciiBattleship);
    }

    @Test
    public void TestGAMEOVERGraphic(){
        System.out.println(Constants.asciiGameOver);
    }
}

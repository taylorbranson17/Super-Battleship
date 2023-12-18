import static org.junit.Assert.*;
import org.junit.Test;


public class StatCellTests {
    StatCell myCell = new StatCell();


    @Test
    //manually added weight, testing cell return;
    public void TestCellOutput(){
        myCell.addWeight(5);
        assertEquals(5, myCell.getWeight());
    }

    @Test
    public void TestCellAfterShip(){
        //adding two ships and testing weight return;
        Ship myShip = new Ship("BattleShip");
        myCell.addShip(myShip);
        myCell.addShip(new Ship("Aircraft Carrier"));
        assertEquals(2, myCell.getWeight());
    }
    
}

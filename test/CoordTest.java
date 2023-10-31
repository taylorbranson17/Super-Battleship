import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CoordTest {
    
    Coord coord; 

    @Before
    public void init(){
        // Do a thing
    }

    @Test 
    public void testValidConstructorIntegers(){
        coord = new Coord(1,1);
        assertTrue(coord.getX() == 1);
        assertTrue(coord.getY() == 1);
    }

    /* 
    // Apparently the integers aren't validated? 
    @Test(expected = Exception.class)
    public void testBadXInteger(){
        coord = new Coord(100,1);
    }

    @Test (expected = Exception.class)
    public void testBadXInteger(){
        coord = new Coord(1,100);
    }
    */

    @Test
    public void testValidConstructorString() throws Exception{
        coord = new Coord("B7");
        assertTrue(coord.getX() == 6); // Column 7, integer 6
        assertTrue(coord.getY() == 1); // Row B, integer 1
    }

    @Test(expected = Exception.class)
    public void testBadStringRow() throws Exception{
        coord = new Coord("X5");
    }

    @Test(expected = Exception.class)
    public void testBadStringColumn() throws Exception{
        coord = new Coord("B20");
    }

    @Test(expected = Exception.class)
    public void testBadStringRowAndColumn() throws Exception{
        coord = new Coord("It's Hip To Be Square");
    }

    @Test
    public void testOneToString() throws Exception{
        coord = new Coord("B7");
        System.out.println(coord.toString());
        assertTrue(coord.toString().equals("B7"));
    }

    @Test
    public void testAllPossibleToStrings() throws Exception{
        String[] testRows = {"A","B","C","D","E","F","G","H","I","J"};
        String[] testColumns = {"1","2","3","4","5","6","7","8","9","10"};
        for (String testRow : testRows){
            for (String testColumn : testColumns){
                coord = new Coord(testRow+testColumn);
                assertTrue(coord.toString().equals(testRow+testColumn));
            }
        }
    }

    @Test
    public void testOneEquality() throws Exception{
        coord = new Coord("A5");
        Coord coord2 = new Coord("A5");
        assertTrue(coord.equals(coord2));
    }

    @Test
    public void testAllPossibleEqualities() throws Exception{
        String[] testRows = {"A","B","C","D","E","F","G","H","I","J"};
        String[] testColumns = {"1","2","3","4","5","6","7","8","9","10"};
        for (String testRow : testRows){
            for (String testColumn : testColumns){
                coord = new Coord(testRow+testColumn);
                Coord coord2 = new Coord(testRow+testColumn);
                assertTrue(coord.equals(coord2));
            }
        }
    }

}

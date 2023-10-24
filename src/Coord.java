import java.util.Objects;

public class Coord {

    private int x;
    private int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public Coord(String value) throws Exception {
        this.y = parseRow(value);
        this.x = parseColumn(value);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    private int parseRow(String value) throws Exception {
        char row = value.toLowerCase().charAt(0);
        int result = Character.getNumericValue(row) - 10; // adjust for 'a' having a value of 10.
        if (result < 0 || result > 9) {
            throw new Exception(row + " is not a valid row.");
        }
        return result;
    }

    private int parseColumn(String value) throws Exception {
        String column = value.substring(1);
        int result = Integer.valueOf(column) - 1; // convert to '0' index from '1' index
        if (result < 0 || result > 9) {
            throw new Exception(column + " is not a valid column.");
        }
        return result;
    }

    @Override
    public String toString(){
        String row = String.valueOf((char) (this.y + 65)).toUpperCase();
        int column = this.x + 1;
        return (row + column);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.x,this.y);
    }

    @Override
    public boolean equals(Object o){
        if(this== o){
            return true;
        }
        if(o == null|| getClass() != o.getClass()){
            return false;
        }
        Coord coord = (Coord) o;
        return ((x == coord.getX()) && (y==coord.getY()));
    }
}

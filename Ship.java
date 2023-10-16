import java.util.ArrayList;

public class Ship {
    private String name;
    private int length;
    private int hitCount = 0;
    private ArrayList<Coord> coords = new ArrayList<Coord>();

    public Ship(String value){
        this.name = value;
        this.length = setLength(value);
    }

    public int getLength(){
        return this.length;
    }
 
    public void setHit(){
        hitCount++;
    }

    public void setCoords(ArrayList<Coord> coords){
        this.coords.addAll(coords);
    }

    public ArrayList<Coord> getCoords(){
        return this.coords;
    }

    public String getName(){
        return this.name;
    }

    private int setLength(String value){
        String lowerVal = value.toLowerCase();    
        switch (lowerVal) {
            case "battleship":  
                return 4;
            case "submarine":
                return 3;
            case "cruiser":
                return 3;
            case "destroyer":
                return 2;
            default:
                return 5;
        }
    }

    public boolean isSunk(){
        return this.hitCount == this.length; 
    }
}
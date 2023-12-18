package Common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ship {
    private String name;
    private int length;
    private int hitCount = 0;
    private ArrayList<Coord> coords = new ArrayList<Coord>();

    public Ship(String value, int length){
        this.name = value;
        this.length = length;
    }

    public int getLength(){
        return this.length;
    }
 
    public void setHit(){
        hitCount++;
    }

    public void setCoords(List<Coord> coords){
        this.coords.addAll(coords);
    }

    public ArrayList<Coord> getCoords(){
        return this.coords;
    }

    public String getName(){
        return this.name;
    }

    public boolean isSunk(){
        return this.hitCount == this.length; 
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.length, this.coords);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ship ship = (Ship) o;
        return ((this.getLength() == ship.getLength()) && (this.getName().equals(ship.getName())) && (this.getCoords().equals(ship.getCoords())));
    }
}
package Theresa;

import java.util.ArrayList;
import Common.Coord;

public class Shooter {

    protected ArrayList<Coord> shotCoords = new ArrayList<Coord>();

    public Coord getShot() {
        Coord shot = shotCoords.get(0);
        shotCoords.remove(0);
        return shot;
    }

    // ************************Test Only Methods********************************
    public ArrayList<Coord> getCoords() {
        return this.shotCoords;
    }
}

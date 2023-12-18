package Theresa;

import Common.Coord;
import Common.Direction;

public class BracketShooter extends Shooter {
    private Coord firstHit;

    public BracketShooter(Coord firstHit) {
        this.firstHit = firstHit;
        genShotCoords();
    }

    private void genShotCoords(){
        int primalX = firstHit.getX();
        int primalY = firstHit.getY();
        for(Direction direct: Direction.values()){
            
            try{
                switch(direct){
                    case N->shotCoords.add(new Coord(primalX, primalY-1));
                    case S->shotCoords.add(new Coord(primalX, primalY+1));
                    case E->shotCoords.add(new Coord(primalX+1, primalY));
                    case W->shotCoords.add(new Coord(primalX-1, primalY));
                }
            } catch (Exception e){
                continue;
            }
        }
   }
}

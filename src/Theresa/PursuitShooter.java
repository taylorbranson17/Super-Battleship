package Theresa;

import Common.*;

public class PursuitShooter extends Shooter {

    private Coord lastHit;
    private Direction pursuitDirection;

    public PursuitShooter(Direction direction, Coord lastHit) {
        this.lastHit = lastHit;
        this.pursuitDirection = direction;
        genShotCoords();
    }


    private void genShotCoords(){
        int x = lastHit.getX();
        int y = lastHit.getY();
        for(int i = 1; i < 4; i++){
            try {
                switch(pursuitDirection){
                case N->shotCoords.add(new Coord(x, y-i));
                case S->shotCoords.add(new Coord(x, y+i));
                case E->shotCoords.add(new Coord(x+i, y));
                case W->shotCoords.add(new Coord(x-i, y));
                }
            } catch (Exception e){
                break;
            }
            
        }
    }
}

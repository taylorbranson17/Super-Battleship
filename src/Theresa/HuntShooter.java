package Theresa;

import Common.*;
import java.util.Random;

public class HuntShooter extends Shooter {
    private int minShipLength;

    public HuntShooter(int shipLength){
        this.minShipLength = shipLength;
        genShotCoords();
    }

    @Override
    public Coord getShot(){
        Random rand = new Random();
        Coord chosenCoord = null;
        try{
            chosenCoord = shotCoords.get(rand.nextInt(shotCoords.size()));
        } catch (Exception e){
            
        }
        shotCoords.remove(chosenCoord);
        return chosenCoord;
    }

    private void genShotCoords() {
        int offset = 0;
        // offset to decrement each row vertically by 1.
        for (int i = 0; i < 10; i++) {
            // making sure vertical offset never goes off board with offset & shiplength
            // accounted for.
            for (int y = 0; y + minShipLength - offset - 1 < 10; y += minShipLength) { // add ship length every
                                                                                       // revolution.
                // creating & adding target coord to 'hunt' list.
                try{
                    shotCoords.add(new Coord(i, y + this.minShipLength - offset - 1));
                } catch (Exception e){

                }
            }
            // once offset reaches negative of target ship length, reset to 0.
            if (offset == minShipLength - 1) {
                offset = 0;
            } else {
                offset++;
            }
        }
    }
}

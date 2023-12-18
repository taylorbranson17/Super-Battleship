package EasyAI;

import java.util.List;
import java.util.Random;

import Common.*;
import Factories.AutoShipFactory;
import Grids.OceanGrid;
import Grids.TargetGrid;

public class EasyAI implements PlayerInterface {

    String name = "John Pannell";
    TargetGrid tGrid = new TargetGrid();
    OceanGrid oGrid = new OceanGrid();
    AutoShipFactory factory = new AutoShipFactory();
    List<Ship> ships;


    @Override
    public Coord takeTurn() {
        Random rand = new Random();
        Coord shotCoord;
        while(true){
            try{
                shotCoord = new Coord(rand.nextInt(10), rand.nextInt(10));
                if(tGrid.isValidShot(shotCoord)){
                    return shotCoord;
                } else {
                    continue;
                }
            } catch (Exception e){
                continue;
            }
        }
    }

    @Override
    public void receiveShotResult(ShotResult result) {
       tGrid.receiveShotResult(result);
    }

    @Override
    public ShotResult receiveShot(Coord shot) {
        return oGrid.receiveShot(shot);
    }

    @Override
    public boolean allShipsSunk() {
        return ships.stream().mapToInt(ship->ship.isSunk()? 1:0).sum() == this.ships.size();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void placeShips() {
        this.ships = factory.getShips();
        oGrid.placeShips(ships);
    }

    @Override
    public void reset() {
        this.tGrid = new TargetGrid();
        this.oGrid = new OceanGrid();
        this.factory = new AutoShipFactory();
        this.ships.clear();
    }
    
}

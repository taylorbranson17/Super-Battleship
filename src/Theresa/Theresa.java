package Theresa;

import Grids.*;
import java.util.List;

import Common.*;
import Factories.AutoShipFactory;

public class Theresa implements PlayerInterface{

    private String name = "Theresa";
    TargetGrid tGrid = new TargetGrid();
    OceanGrid oGrid = new OceanGrid();
    private List<Ship> ships;
    private AutoShipFactory factory = new AutoShipFactory();
    private ShotMachine shotMachine = new ShotMachine();

    @Override
    public Coord takeTurn() {
        Coord shot = null;
        while(true){
            shot = shotMachine.getShot();
            if(tGrid.isValidShot(shot)){
                return shot;
            }//else, try again.
        }
    }

    @Override
    public void receiveShotResult(ShotResult result) {
        tGrid.receiveShotResult(result);
        shotMachine.receiveResult(result);
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
    public void reset(){
        tGrid = new TargetGrid();
        oGrid = new OceanGrid();
        factory = new AutoShipFactory();
        shotMachine = new ShotMachine();
        this.ships.clear();
    }
    
}

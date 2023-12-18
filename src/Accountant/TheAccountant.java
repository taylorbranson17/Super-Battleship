package Accountant;
import java.util.List;

import Common.*;
import Factories.AutoShipFactory;
import Grids.OceanGrid;

public class TheAccountant implements PlayerInterface{
    
    private String name = "The Accountant";
    private StatGrid stGrid = new StatGrid();
    private List<Ship> ships;
    private AutoShipFactory factory = new AutoShipFactory();
    private OceanGrid oGrid = new OceanGrid();

    public Coord takeShot(){
        return stGrid.getNextShot();
    }

    public void receiveShotResult(ShotResult result){
        this.stGrid.receiveShotResult(result);
    }

    @Override
    public Coord takeTurn() {
        return stGrid.getNextShot();
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

    public void printBoard(){
        System.out.println(stGrid.print() + "\n\n");
        System.out.println(oGrid.print());
    }

    @Override
    public void placeShips() {
        this.ships = factory.getShips();
        oGrid.placeShips(ships);
    }

    @Override
    public void reset(){
        stGrid = new StatGrid();
        factory = new AutoShipFactory();
        oGrid = new OceanGrid();
        this.ships.clear();
    }
}

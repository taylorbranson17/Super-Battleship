package Common;

import java.util.List;
import Factories.*;
import Grids.*;

public class Player implements PlayerInterface {
    private String name;
    private List<Ship> ships;
    private ShipFactory factory;
    private TargetGrid tGrid = new TargetGrid();
    private OceanGrid oGrid = new OceanGrid();

    public Player(String name) {
        this.name = name;
    }

    @Override
    public Coord takeTurn() {
        printBoard();
        while (true) {
            String value = ConsoleHelper.getInput("Enter the shot you would like to take: ");
            try {
                Coord shotCoord = new Coord(value);
                if (tGrid.isValidShot(shotCoord)) {
                    return shotCoord;
                } else {
                    System.out.println("That shot's already been taken- please try again.");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("That's not a valid coordinate- please try again.");
            }
        }
    }

    @Override
    public ShotResult receiveShot(Coord shot) {
        return oGrid.receiveShot(shot);
    }

    @Override
    public void receiveShotResult(ShotResult result) {
        tGrid.receiveShotResult(result);
    }

    private void printBoard() {
        System.out.println(tGrid.print() + "\n\n");
        System.out.println(oGrid.print());
    }

    @Override
    public void placeShips() {
        int factoryChoice = ConsoleHelper.getInputBetween(
                "Please select how you want to place your ships, " + name + ":\n1) Manually\n2) Automatically", 0, 3,
                "That's not a valid option.");
        switch (factoryChoice) {
            case 2:
                this.factory = new AutoShipFactory();
                this.ships = factory.getShips();
                oGrid.placeShips(ships);
                break;
            case 1:
                this.factory = new ManualShipFactory();
                this.ships = factory.getShips();
                for (Ship ship : ships) {
                    System.out.println(oGrid.print());
                    factory.placeAShip(ship);
                    oGrid.placeShips(ships);
                    Constants.clear();
                }
                break;
        }

    }

    @Override
    public boolean allShipsSunk() {
        return ships.stream().mapToInt(ship -> ship.isSunk() ? 1 : 0).sum() == ships.size();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void reset() {
        tGrid = new TargetGrid();
        oGrid = new OceanGrid();
        this.ships.clear();
    }
}

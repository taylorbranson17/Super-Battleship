package Grids;

import java.util.ArrayList;
import java.util.List;

import Common.ShotResult;
import Common.Ship;
import Common.Coord;

public class OceanGrid extends Grid {

    ArrayList<Ship> ships = new ArrayList<Ship>();

    public OceanGrid() {
        super();
    }

    public ShotResult receiveShot(Coord shot) {
        Cell targetCell = getCellatXY(shot);
        ShotResult result;
        if (targetCell.getState() == CellState.OCCUPIED) {
            targetCell.setState(CellState.HIT);
            if (targetCell.getShip().isSunk()) {
                result = ShotResult.SUNK;
                result.setShip(targetCell.getShip().getName());
            } else {
                result = ShotResult.HIT;
            }
        } else {
            targetCell.setState(CellState.MISS);
            result = ShotResult.MISS;
        }
        result.setCoord(shot);
        return result;
    }

    public void placeShips(List<Ship> playerShips) {
        for (Ship ship : playerShips) {
            for (Coord coord : ship.getCoords()) {
                Cell targetCell = getCellatXY(coord);
                targetCell.setShip(ship);
            }
        }
    }
}

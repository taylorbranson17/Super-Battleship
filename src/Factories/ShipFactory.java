package Factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Common.*;

public abstract class ShipFactory {
    protected ArrayList<Coord> totalShipCoords = new ArrayList<Coord>();
    protected List<Ship> ships = new ArrayList<Ship>();
    protected final String[] shipNames = new String[] { "Battleship", "Destroyer", "Submarine", "Carrier",
            "Patrol Boat" };

    public ShipFactory() {
        Constants.ShipMap.forEach((k, v) -> ships.add(new Ship(k, v)));
    }

    abstract protected Coord getLeadCoord(String shipName);

    abstract protected Direction getDirection(List<Direction> directions, String shipName);

    protected ArrayList<Direction> filterShipOverlapEveryDirection(Coord leadCoord, int length) {
        ArrayList<Direction> boardValidDirections = filterBoardBounds(leadCoord, length);
        ArrayList<Direction> inValidDirections = new ArrayList<Direction>();
        for (Direction direct : boardValidDirections) {
            ArrayList<Coord> shipCoords = genShipCoords(leadCoord, length, direct);
            if (!validateCoords(shipCoords)) {
                inValidDirections.add(direct);
            }
        }
        boardValidDirections.removeAll(inValidDirections);
        return boardValidDirections;
    }

    protected boolean validateCoords(ArrayList<Coord> targetList) {
        for (Coord coord : targetList) {
            if (totalShipCoords.contains(coord)) {
                return false;
            }
        }
        return true;
    }

    protected ArrayList<Coord> genShipCoords(Coord leadCoord, int length, Direction direction) {
        ArrayList<Coord> shipCoords = new ArrayList<Coord>();
        for (int i = 0; i < length; ++i) {
            int primalY = leadCoord.getY();
            int primalX = leadCoord.getX();
            switch (direction) {
                case N -> primalY -= i;
                case S -> primalY += i;
                case W -> primalX -= i;
                case E -> primalX += i;
            }
            try {
                shipCoords.add(new Coord(primalX, primalY));
            } catch (Exception e) {

            }

        }
        return shipCoords;
    }

    protected ArrayList<Direction> filterBoardBounds(Coord initialCoord, int length) {
        ArrayList<Direction> validDirections = new ArrayList<Direction>(Arrays.asList(Direction.values()));
        ArrayList<Direction> invalidDirections = new ArrayList<Direction>();
        if (initialCoord.getY() - length + 1 < 0) {
            invalidDirections.add(Direction.N);
        }
        if (initialCoord.getY() + length - 1 > 9) {
            invalidDirections.add(Direction.S);
        }
        if (initialCoord.getX() < length - 1) {
            invalidDirections.add(Direction.W);
        }
        if (initialCoord.getX() + length - 1 > 9) {
            invalidDirections.add(Direction.E);
        }
        validDirections.removeAll(invalidDirections);
        return validDirections;
    }

    public List<Ship> getShips() {
        return this.ships;
    }

    public void placeAShip(Ship ship) {
        Coord leadCoord = null;
        List<Direction> validDirects = null;
        while (true) {
            leadCoord = getLeadCoord(ship.getName());
            validDirects = filterShipOverlapEveryDirection(leadCoord, ship.getLength());
            if(validDirects.size() > 0){
                break;
            } else if(this instanceof ManualShipFactory) {
                System.out.println("You've chosen an incompatible coordinate. Please choose again.");
            }
        }
        Direction chosenDirection = getDirection(validDirects, ship.getName());
        ArrayList<Coord> shipCoords = genShipCoords(leadCoord, ship.getLength(), chosenDirection);
        ship.setCoords(shipCoords);
        this.totalShipCoords.addAll(shipCoords);
    }
}

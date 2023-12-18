package Accountant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import Common.*;

public class StatGrid {

    private StatCell[][] grid;
    private String[] rowHeaders = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
    private int[] colHeaders = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    private ArrayList<Ship> ships = genShips();
    private Set<Ship> hitShips = new HashSet<Ship>();

    public StatGrid() {
        this.grid = new StatCell[10][10];
        for (int i = 0; i < 10; i++) {
            for (int y = 0; y < 10; y++) {
                this.grid[i][y] = new StatCell();
            }
        }
        placeShips();
    }

    public String print() {
        String boardString = "";
        boardString += formatColHeaders();
        for (int i = 0; i < 10; i++) {
            boardString += this.rowHeaders[i] + " |";
            for (int y = 0; y < 10; y++) {
                // two space seperator for each cell.
                boardString += "  " + getCellatXY(y, i).print();
            }
            boardString += "\n";
        }
        return boardString;
    }

    private StatCell getCellatXY(int column, int row) {
        return this.grid[column][row];
    }

    private StatCell getCellatXY(Coord coord) {
        return this.grid[coord.getX()][coord.getY()];
    }

    private String formatColHeaders() {

        // initial single space seperator + initial two from first header to accomodate
        // Row labels
        String tempHeader = "   ";
        for (int col : this.colHeaders) {
            // two space seperator for Col headers
            tempHeader += "  " + col;
        }
        tempHeader += "\n----------------------------------\n";
        return tempHeader;
    }

    private void placeShips() {
        for (Ship ship : this.ships) {
            for (Coord coord : ship.getCoords()) {
                StatCell cell = getCellatXY(coord);
                cell.addShip(ship);
            }
        }
    }

    public void genCSVData(String fileName) throws Exception {
        List<String[]> data = new ArrayList<String[]>();
        // i=row index
        for (int i = 0; i < 10; i++) {
            String[] rowData = new String[10];
            // y=column index
            for (int y = 0; y < 10; y++) {
                int weight = getCellatXY(y, i).getWeight();
                rowData[y] = Integer.toString(weight);
            }
            data.add(rowData);
        }
        CSVPrinter cp = new CSVPrinter(fileName);
        cp.whenGivenDataString_CreateAndOutputFile(data);
    }

    public void receiveShotResult(ShotResult result) {
        Coord shotCoord = result.getCoord();
        StatCell targetCell = getCellatXY(shotCoord);

        switch (result) {
            case HIT:
                targetCell.setHit();
                hitShips.addAll(targetCell.getShips());
                targetCell.hitWeight();
                prioritizeCoords();
                try {
                    genCSVData("hitHeat.csv");
                } catch (Exception e) {

                }
                break;

            case MISS:
                ArrayList<Ship> shipsToDrop = targetCell.getShips();
                this.ships.removeAll(shipsToDrop);
                this.hitShips.removeAll(shipsToDrop);
                targetCell.dropWeight();
                recalcWeights();
                try{
                    genCSVData("missHeat.csv");
                } catch (Exception e){

                }
                break;

            case SUNK:
                targetCell.setHit();
                ArrayList<Ship> sunkenDropShips = dropCoorelatedShips(result);
                this.ships.removeAll(sunkenDropShips);
                this.hitShips.removeAll(sunkenDropShips);
                targetCell.dropWeight();
                recalcWeights();
                try {
                    genCSVData("sunkHeat.csv");
                } catch (Exception e){
                    
                }
                break;

            default:
                break;
        }
    }

    private void recalcWeights() {
        for (int i = 0; i < 10; i++) {
            for (int y = 0; y < 10; y++) {
                getCellatXY(i, y).reset();
            }
        }
        placeShips();
        prioritizeCoords();
    }

    public Coord getNextShot() {
        int currentWeight = 0;
        int coordX = 0;
        int coordY = 0;
        for (int i = 0; i < 10; i++) {
            for (int y = 0; y < 10; y++) {
                int weight = getCellatXY(i, y).getWeight();
                if (weight > currentWeight) {
                    coordX = i;
                    coordY = y;
                    currentWeight = weight;
                }
            }
        }
        Coord shotCoord = null;
        try {
            shotCoord = new Coord(coordX, coordY);
        } catch (Exception e) {

        }
        return shotCoord;
    }

    private ArrayList<Ship> genShips() {
        ArrayList<Ship> ships = new ArrayList<Ship>();
        Set<String> shipNames = Constants.ShipMap.keySet();
        // b controls direction of generated ships. 0 = vertical, 1 = horizontal
        for (int b = 0; b < 2; b++) {
            // grabs a unique ship name
            for (String name : shipNames) {
                // controls the vertical offset of a row of generated ships.
                int offset = 0;
                boolean shipFlag = true;
                while (shipFlag) {
                    // generates one row of vertical ships of type 'name'
                    for (int i = 0; i < 10; i++) {
                        Ship ship = new Ship(name, Constants.ShipMap.get(name));
                        ArrayList<Coord> tempShipCoords = new ArrayList<Coord>();
                        // generates coordinate set for each ship
                        for (int y = 0; y < ship.getLength(); y++) {
                            // checks to make sure the ship will fit vertically, with alloted offset. Breaks
                            // loop, starts generating new ship.
                            if (offset + ship.getLength() - 1 > 9) {
                                shipFlag = false;
                                break;
                            }
                            if (b < 1) {
                                try {
                                    tempShipCoords.add(new Coord(i, y + offset));
                                } catch (Exception e) {

                                }

                            } else if (b == 1) {
                                try {
                                    tempShipCoords.add(new Coord(y + offset, i));
                                } catch (Exception e) {

                                }

                            }

                        }
                        if (shipFlag) {
                            ship.setCoords(tempShipCoords);
                            ships.add(ship);
                        }
                    }
                    offset++;
                }

            }
        }
        return ships;
    }

    private ArrayList<Ship> dropCoorelatedShips(ShotResult result) {
        String shipName = result.getShip();
        ArrayList<Ship> shipsToDrop = new ArrayList<>();
        ArrayList<Coord> refShipCoords = null;
        for (Ship ship : hitShips) {
            if (ship.isSunk() && ship.getName().equals(shipName) && ship.getCoords().contains(result.getCoord())) {
                refShipCoords = ship.getCoords();
                for (Coord coord : refShipCoords) {
                    getCellatXY(coord).dropWeight();
                }
                break;
            }
        }
        for (Ship ship1 : this.ships) {
            if (ship1.getName().equals(shipName)) {
                shipsToDrop.add(ship1);
            } else {
                for (Coord coord : refShipCoords) {
                    if (ship1.getCoords().contains(coord)) {
                        shipsToDrop.add(ship1);
                    }
                }
            }
        }
        return shipsToDrop;
    }

    private void prioritizeCoords() {
        for (Ship ship : this.hitShips) {
            for (Coord coord : ship.getCoords()) {
                getCellatXY(coord).addWeight(4);
            }
        }
    }
}

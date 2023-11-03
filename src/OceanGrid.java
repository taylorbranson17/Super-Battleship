import java.util.ArrayList;

public class OceanGrid extends Grid {

    ArrayList<Ship> ships = new ArrayList<Ship>();

    public OceanGrid(int config) {
        super();
        FactoryDecision(config);
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

    public boolean allShipsSunk() {
        int status = 0;
        for (Ship ship : this.ships) {
            if (ship.isSunk()) {
                status++;
            }
        }
        return status == this.ships.size();
    }

    private void placeShips() {
        for (Ship ship : this.ships) {
            for (Coord coord : ship.getCoords()) {
                Cell targetCell = getCellatXY(coord);
                targetCell.setShip(ship);
            }
        }
    }
//Test only methods #################################################
    private void testPlaceShip(Ship ship){
        this.ships.add(ship);
        for(Coord coord: ship.getCoords()){
            getCellatXY(coord.getX(), coord.getY()).setShip(ship);
        }
    }

    private int getShips(){
        return this.ships.size();
    }


















//Test only methods #################################################
    


// commented out to eliminate dependency errors
    private void FactoryDecision(int value) {
        if (value == 1) {
            new AutoShipFactory(this.ships);
            placeShips();
        } else {
            ManualShipFactory myManFact = new ManualShipFactory(this.ships);
            for(Ship ship: this.ships){
                System.out.println(print() + "\n");
                myManFact.placeAShip(ship);
                this.placeShips();
            }
            System.out.println(print() + "\n");
        }
    }
}

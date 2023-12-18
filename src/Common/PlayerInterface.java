package Common;

public interface PlayerInterface {
    
    public Coord takeTurn();
    public void receiveShotResult(ShotResult result);
    public ShotResult receiveShot(Coord shot);
    public boolean allShipsSunk();
    public String getName();
    public void placeShips();
    public void reset();
}

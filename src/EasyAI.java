import java.util.Random;

public class EasyAI implements PlayerInterface{
    String name = "John Pannell";
    TargetGrid tGrid = new TargetGrid();
    OceanGrid oGrid = new OceanGrid(1);

    @Override
    public Coord takeTurn() {
        Random rand = new Random();
        while(true){
            Coord shot = new Coord(rand.nextInt(10), rand.nextInt(10));
            if(tGrid.isValidShot(shot)){
                return shot;
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

    @Override
    public Boolean validateTurn() {
        return !oGrid.allShipsSunk();
    }

    @Override
    public String getName() {
        return this.name;
    }
    
}

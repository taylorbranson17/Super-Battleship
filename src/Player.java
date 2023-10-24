public class Player  implements PlayerInterface{
    private String name;
    private TargetGrid targetGrid;
    private OceanGrid oceanGrid;

    public Player(String name) {
        this.name = name;
        this.targetGrid = new TargetGrid();
        this.oceanGrid = new OceanGrid();
    }

    public String getName() {
        return name;
    }

    public ShotResult takeShot(Coord shot) {
        return oceanGrid.receiveShot(shot);
    }

    @Override
    public Coord takeTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'takeTurn'");
    }

    @Override
    public ShotResult recieveShot() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recieveShot'");
    }

    @Override
    public void recieveShotResult() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recieveShotResult'");
    }

    @Override
    public Boolean validateTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateTurn'");
    }
}

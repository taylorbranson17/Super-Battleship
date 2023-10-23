public class Player {
    private String name;
    private TargetGrid targetGrid;
    private OceanGrid oceanGrid;

    public Player(String name) {
        this.name = name;
        this.targetGrid = new TargetGrid();
        this oceanGrid = new OceanGrid();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    

    public void setName(String name) {
        this.name = name;
    }

    public TargetGrid getTargetGrid() {
        return targetGrid;
    }

    public OceanGrid getOceanGrid() {
        return oceanGrid;
    }

    public ShotResult takeShot(Coord shot) {
        return oceanGrid.receiveShot(shot);
    }
}

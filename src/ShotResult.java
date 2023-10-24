public enum ShotResult {
    HIT,
    MISS,
    SUNK;

    private Coord coord;
    private String shipName;

    public void setCoord(Coord coord){
        this.coord = coord;
    }

    public void setShip(String shipName){
        this.shipName = shipName;
    }

    public String getShip(){
        return this.shipName;
    }

    public Coord getCoord(){
        return this.coord;
    }

    @Override
    public String toString(){
        switch(this){
            case MISS:
                return "MISSED";
            case HIT:
                return "HIT";
            default:
                return "SUNK";
        }
    }
}

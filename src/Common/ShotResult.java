package Common;

import Theresa.Event;

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
        return Constants.getASCII(this);
    }

    public Event getEvent(){
        switch(this){
            case HIT:
                return Event.HIT;
            case MISS:
                return Event.MISS;
            case SUNK:
                return Event.SUNK;
            default:
                return null;
        }
    }
}

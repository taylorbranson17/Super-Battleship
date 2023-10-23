public class Cell {

    private CellState state = CellState.EMPTY;
    private Ship shipRef;
    private String shipSymbol;
    
    public Ship getShip(){
        return this.shipRef;
    }

    public CellState getState(){
        return this.state;
    }

    public void setState(CellState state){
        if(this.shipRef != null && state == CellState.HIT){
            this.shipRef.setHit();
        }
        this.state = state;
    }

    public void setShip(Ship value){
        this.shipRef = value;
        shipSymbol = value.getName().substring(0,1).toUpperCase();
        this.state = CellState.OCCUPIED;
    }

    public String print(){
        switch(this.state){
            case HIT:
                return "#";
            case OCCUPIED:
                return this.shipSymbol;
            case MISS:
                return "~";
            default:
                return ".";
        }
    }
}

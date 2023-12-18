package Common;

public enum Direction {
    N,
    S,
    E,
    W;
    
    public Direction getOpp(){
        switch(this){
            case N:
                return Direction.S;
            case S:
                return Direction.N;
            case E:
                return Direction.W;
            case W:
                return Direction.E;
            default:
                return this;
        }
    }
}

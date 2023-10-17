package SuperBattleship.src;
public class TargetGrid extends Grid {

    public TargetGrid(){
        super();
    }

    public void receiveShotResult(ShotResult result){
        Coord coord = result.getCoord();
        Cell targetCell = getCellatXY(coord.getX(), coord.getY());
        switch(result){
            case HIT:
                targetCell.setState(CellState.HIT);
                break;
            case MISS:
                targetCell.setState(CellState.MISS);
                break;
            case SUNK:
                targetCell.setState(CellState.HIT);
                break;
            default:
                break;
        }
    }

    public boolean isValidShot(Coord shot){
        Cell targCell = getCellatXY(shot.getX(), shot.getY());
        CellState state = targCell.getState();
        switch(state){
            case EMPTY:
                return true;
            default:
                return false;
        }
    }
}

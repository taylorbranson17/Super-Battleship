package Grids;

import Common.ShotResult;
import Common.Coord;
import java.util.ArrayList;

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

    //Test OBJECTS ONLY**************** to be deleted after use.
    protected String[] rowHeaders = new String[]{"A","B","C","D","E","F","G","H","I","J"};
    private int[] colHeaders = new int[]{1,2,3,4,5,6,7,8,9,10};

    public String testPrint(ArrayList<Coord> coords){
        for(Coord coord: coords){
            getCellatXY(coord).setState(CellState.HIT);
        }
        String boardString ="";
        boardString += formatColHeaders();
        for(int i = 0; i < 10; i++){
            boardString += this.rowHeaders[i] + " |";
            for(int y = 0; y < 10; y++){
                //two space seperator for each cell.
                boardString += "  " + getCellatXY(y, i).print();
            }
            boardString += "\n";
        }
        return boardString;
    }

    private String formatColHeaders(){
        //initial single space seperator + initial two from first header to accomodate Row labels
        String tempHeader = "   ";
        for(int col: this.colHeaders){
            //two space seperator for Col headers
            tempHeader += "  " + col;
        }
        tempHeader += "\n----------------------------------\n";
        return tempHeader;
    }
}

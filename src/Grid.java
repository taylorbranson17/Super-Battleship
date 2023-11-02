
abstract class Grid {
    
    protected Cell[][] grid;
    protected String[] rowHeaders = new String[]{"A","B","C","D","E","F","G","H","I","J"};
    private int[] colHeaders = new int[]{1,2,3,4,5,6,7,8,9,10};

    public Grid(){
        Cell[][] gridTemplate = new Cell[10][10];
        for(int i = 0; i < 10; i++){
            for(int y=0; y<10; y++){
                gridTemplate[i][y] = new Cell();
            }
        }
        this.grid = gridTemplate;
    }

    public String print(){
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

    //made public ONLY FOR TESTING
    public Cell getCellatXY(int column, int row){
        return this.grid[column][row];
    }

    //made public ONLY FOR TESTING
    public Cell getCellatXY(Coord coord){
        return this.grid[coord.getX()][coord.getY()];
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

    /*ONLY USED FOR TESTING TO ASSURE THE RIGHT NUMBER OF CELLS */
    private Cell[][] getGrid(){
        return this.grid;
    }
}

public class Player  implements PlayerInterface{
    private String name;
    private TargetGrid targetGrid;
    private OceanGrid oceanGrid;
   

    public Player(String name) {
        this.name = name;
        this.targetGrid = new TargetGrid();
        this.oceanGrid = new OceanGrid();
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Coord takeTurn() {
        Coord shot = null;
        while (true){
            try {
                String input = ConsoleHelper.getInput(name + ", enter your shot coordinates (e.g., A1): ");
                shot = new Coord(input);
                if(!targetGrid.isValidShot(shot)){
                    throw new Exception();
                } 
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter valid coordinates.");
            }
        }
        return shot;
    }


    @Override
    public ShotResult receiveShot(Coord shot) {
        return oceanGrid.receiveShot(shot);
    }

    @Override
    public void receiveShotResult(ShotResult result) {
       targetGrid.receiveShotResult(result);
    }

    @Override
    public Boolean validateTurn() {
       return !oceanGrid.allShipsSunk();
    }

 }

   


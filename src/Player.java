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
        Coord shot = null;

        try {
            String input = ConsoleHelper.getInput(name + ", enter your shot coordinates (e.g., A1): ");
            shot = new Coord(input);
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid coordinates.");
        }

        return shot;
    }


    @Override
    public ShotResult receiveShot() {
        return oceanGrid.receiveShot(null);
    }

    @Override
    public void recieveShotResult() {
       targetGrid.receiveShotResult(null);
    }

       private boolean shotIsValid(Coord shot) {
        if (shot != null) {
            // Check if the shot is within the valid range.
            return true;
        }
        return false;
    }
    
    @Override
    public Boolean validateTurn() {
        // check if the shot is valid and if it's the player's turn.
        if (shotIsValid(shot)) {
            return true; 
        }
        return false;
    }

 }

   


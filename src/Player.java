public class Player implements PlayerInterface {
    private String name;
    private TargetGrid targetGrid = new TargetGrid();
    private OceanGrid oceanGrid;

    public Player(String name) {
        this.name = name;
        config();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Coord takeTurn() {
        printBoard();
        Coord shot = null;
        while (true) {
            try {
                String input = ConsoleHelper.getInput(name + ", enter your shot coordinates (e.g., A1): ");
                shot = new Coord(input);
                if (!targetGrid.isValidShot(shot)) {
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

    private void config() {
        System.out.println(this.name + ", how would you like to place your ships?");
        System.out.println("1) Automatically\n2) Manually\n");
        while (true) {
            String value = ConsoleHelper.getInput("Please enter your choice: ");
            try {
                int factDecis = Integer.valueOf(value);
                if (factDecis > 2 || factDecis < 0) {
                    throw new Exception();
                } else {
                    oceanGrid = new OceanGrid(factDecis);
                    break;
                }
            } catch (Exception e) {
                System.out.println("That's not a valid option, please try again.");
            }
        }

    }

    //made public for testing only.
    private void printBoard(){
        System.out.println(oceanGrid.print() + "\n\n\n");
        System.out.println(targetGrid.print());
    }

}

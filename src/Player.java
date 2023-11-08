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
        while (true) {
            try {
                String input = ConsoleHelper.getInput(name + ", enter your shot coordinates (e.g., A1): ");
                Coord shot = new Coord(input);
                if (targetGrid.isValidShot(shot)) {
                    return shot;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter valid coordinates.");
            }
        }
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
        int shipFact = ConsoleHelper.getInputBetween(this.name + ",please enter how you would like to place your ships:\n1) Manually\n2) Automatically", 0, 3, name)-1;
        oceanGrid = new OceanGrid(shipFact);
    }

    //made public for testing only.
    private void printBoard(){
        System.out.println(targetGrid.print() + "\n\n\n");
        System.out.println(oceanGrid.print());
    }
}

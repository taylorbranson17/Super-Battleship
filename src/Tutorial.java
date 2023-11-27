
public class Tutorial {
    TargetGrid tGrid = new TargetGrid();
    OceanGrid oGrid = new OceanGrid(1);

    public Tutorial() {
        boolean decision = welcomeBegin();
        if (decision) {
            shipPlacement();
            takingShots();
            ConsoleHelper.getInput(
                    "That's a wrap! You now know how to play, we look forward to seeing you on the Battle-grids! >");
        }
    }

    private boolean welcomeBegin() {
        Constants.clear();
        String value = ConsoleHelper.getInput(
                "Welcome to the Super Battleship Tutorial!\nWe'll give you a quick rundown on the playing of the game...\n\nHit enter to continue, or 'q' to quite the tutorial >");
        ConsoleHelper.getInput("**Hint** any '>' means to hit enter to continue >");
        if (value.equals("q")) {
            return false;
        }
        Constants.clear();
        System.out.println(
                "This is the basic layout of the game- you will have a target grid marking the shots you take, and ocean grid containing your ships. \n(We've had our algorithm position these ships, which is also an option for you)\n");
        printExBoard();
        ConsoleHelper.getInput(
                "\nA hit on a position is marked with a '#', and a miss is a '~'.\nOpponents shots are recorded on your Ocean Grid, and your shots are recorded on your Target Grid- just like the real game!\n\nOnce you're familiar, hit enter to continue >");
        Constants.clear();
        ConsoleHelper.getInput(
                "\n\nShips are represented by the first letter of their name: 'B' is a Battleship, 'C' is a Carrier, 'P' for Patrol Boat, 'S' for Submarine, and 'D' for Destroyer >");
        Constants.clear();
        return true;
    }

    private void printExBoard() {
        System.out.println(tGrid.print());
        System.out.println("Target Grid ^ \n");
        System.out.println("\nOcean Grid V");
        System.out.println(oGrid.print());
    }

    private void shipPlacement() {
        ConsoleHelper.getInput(
                "At the beginning of the game, you'll be asked how you'd like to position your ships. Automatic does it for you, manually lets you choose >");
        Constants.clear();
        System.out.println("\nEach ship is positioned by it's 'lead' cell, then the direction you want it to go...");
        System.out.println(
                "\nThe directions options for a ship are automatically calculated for you based on the ship length, and position you choose on the board. We only give you valid options to place your ships- no overlapping, or running off the board!");
        ConsoleHelper.getInput(
                "\n\nOur directions are listed using ordinals (N,S,E,W like a compass)-> North = UP, South = DOWN, East = RIGHT, and West = LEFT from the 'lead' position you enter > ");
        Constants.clear();
        int testChoice = ConsoleHelper.getInputBetween(
                "We'll give you an example to test it out:\n\nThese are the directions you can go for your 'Battleship':\n1) N\n2) S\n3) E\n4) W\n\n(Now type the number next to your chosen direction here...)",
                0, 5, "That's not a valid option.");
        Constants.clear();
        switch (testChoice) {
            case 1:
                ConsoleHelper.getInput("\nYou positioned your Battleship to the NORTH! Good Job! >");
                break;
            case 2:
                ConsoleHelper.getInput("\nYou positioned your Battleship to the SOUTH! Good Job! >");
                break;
            case 3:
                ConsoleHelper.getInput("\nYou positioned your Battleship to the EAST! Good Job! >");
                break;
            case 4:
                ConsoleHelper.getInput("\nYou positioned your Battleship to the WEST! Good Job! >");
                break;
        }
        ConsoleHelper.getInput("That's about the gist of ship positioning...hit enter to continue >");
        Constants.clear();
    }

    private void takingShots() {
        System.out.println(
                "Taking shots is easy when it's your turn- just punch in the letter/column for the position you want to fire on. We'll let you know if you make a typo, or have already taken that shot.\n\n");
        ConsoleHelper.getInput(
                "We'll then return you to a 'neutral' screen where all players can see the position and result of the shot. (Without having to worry about prying on anyone elses' board)\nThis also indicates who's up next, and gives you a chance to hide your board from your opponents vision, before printing them out for your turn... >");
        Constants.clear();
    }
}

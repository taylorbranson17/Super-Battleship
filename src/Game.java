import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;

// Is this working?

public class Game {

    // INSTANCE VARIBLES--------------------

    List<PlayerInterface> players = new ArrayList<PlayerInterface>(); // The players participating in this game
    int currentPlayer = 0; // The player currently making decisions
    int opposingPlayer = 1; // The player opposing the one making decisions

    // PUBLIC METHODS--------------------
    public Game() {
        config();
    }

    // Get the game moving
    public void play() {
        clear();
        System.out.println("Welcome to Super Battleship!\nMay your shots be true, and nerves hard as steel!");
        ConsoleHelper.getInput("Hit enter to launch into game >");
        while (players.get(currentPlayer).validateTurn()) {
            // Check to make sure the chosen 'currentPlayer' still has ships to sink.
            ConsoleHelper.getInput(
                    "\n\n\nIt's " + players.get(currentPlayer).getName() + "'s turn! Press [Enter] to continue >");

            clear();

            // The 'currentPlayer' then takes a turn...a.k.a. takes a shot.
            Coord shot = players.get(currentPlayer).takeTurn();

            // That shot is returned, to be passed to the opposingPlayer to receive.
            ShotResult shotResult = players.get(opposingPlayer).receiveShot(shot);

            // The opposingPlayer returns a ShotResult which is then passed to the
            // currentPlayer's TargetGrid
            players.get(currentPlayer).receiveShotResult(shotResult);

            // The results are printed to a 'neutral screen' where both players can see the
            // result
            clear(); // Clear the screen
            String currentPlayerName = this.players.get(currentPlayer).getName();
            // print out result to neutral screen.
            System.out.println(
                    "Player " + currentPlayerName + " Fired at " + shot.toString() + "!\nand " + shotResult.toString()
                            + "!");

            // After the loop, the currentPlayer & opposingPlayer are toggled/swapped, so
            // it's the next person's turn & it starts again.
            currentPlayer = (currentPlayer == 0) ? 1 : 0;
            opposingPlayer = (opposingPlayer == 1) ? 0 : 1;
        }
    }

    // Configure settings for the game overall
    public void config() {
        clear();
        // Ask the player if they want a tutorial
        boolean tutorialOption = yesOrNoPrompt("Would you like to view a tutorial?");
        if (tutorialOption == true) {
            tutorialOption();
        }
        clear();
        int configChoice = ConsoleHelper.getInputBetween(
                "Please enter your choice of opponent:\n1) AIPlayer\n2) Another human", 0, 3,
                "Invalid option, please try again.");
        configPlayers(configChoice);
    }

    // PRIVATE METHODS--------------------

    // Allow the player to enter their name and decide whether they will play
    // against a human or a CPU
    private void configPlayers(int value) {
        List<String> playerNames;
        switch (value) {
            case 2:
                String playerString = ConsoleHelper
                        .getInput("Please enter the names of the players, seperated by a comma: ");
                playerNames = formatNames(playerString);
                clear();
                ConsoleHelper.getInput("You will now choose how to position your ships. " + playerNames.get(0)
                        + " will begin- hit enter when ready >");
                clear();
                for (int i = 0; i < 2; i++) {
                    this.players.add(new Player(playerNames.get(i)));
                }
                break;
            case 1:
                String playerName = ConsoleHelper.getInput("Player, please enter your name: ");
                playerNames = formatNames(playerName);
                ConsoleHelper.getInput("You will now choose how to position your ships, " + playerNames.get(0)
                        + ". Hit enter to begin >");
                clear();
                this.players.add(new Player(playerNames.get(0)));
                this.players.add(new EasyAI());
                break;
        }
    }

    // Shows a tutorial on how to play the game
    private void tutorialOption() {
        // Print out... like... tutorial stuff, man
        System.out.println("Tutorial under construction, sorry!");
    }

    // Separate comma-delimited list of names (if necessary)

    private List<String> formatNames(String nameString) {
        List<String> names = Arrays.asList(nameString.split(","));
        return names.stream()
                .map(name -> name.toLowerCase())
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .collect(Collectors.toList());
    }

    // Clear the screen!
    private void clear() {
        System.out.print("\033[H\033[2J");
    }

    // Give the player a yes/no prompt and process their answer
    private boolean yesOrNoPrompt(String prompt) {
        // Assemble a list of valid "yes" responses
        ArrayList<String> validYesses = new ArrayList<String>();
        validYesses.add("y");
        validYesses.add("yes");
        validYesses.add("yup");
        validYesses.add("ok");

        // Get the player's response
        String response = ConsoleHelper.getInput(prompt + " Type y/n and press [Enter]: ");

        // Check if their response is a yes
        if (validYesses.contains(response.toLowerCase())) {
            return true;
        } else {
            return false;
        }

    }
}
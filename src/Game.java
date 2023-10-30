import java.util.List;
import java.util.ArrayList;

// Is this working?

public class Game {

    // INSTANCE VARIBLES--------------------

    List<PlayerInterface> players = new ArrayList<PlayerInterface>(); // The players participating in this game
    int currentPlayer = 0; // The player currently making decisions
    int opposingPlayer = 1; // The player opposing the one making decisions

    // PUBLIC METHODS--------------------
    public Game(){
        config();
    }

    // Get the game moving 
    public void play(){
        while (players.get(currentPlayer).validateTurn()){
            // Check to make sure the chosen 'currentPlayer' still has ships to sink. 
            ConsoleHelper.getInput("\n\n\nIt's "+players.get(opposingPlayer).getName()+"'s turn! Press [Enter] to continue >");

            clear();

            // The 'currentPlayer' then takes a turn...a.k.a. takes a shot.
            Coord shot = players.get(currentPlayer).takeTurn(); 

            // That shot is returned, to be passed to the opposingPlayer to receive.
            ShotResult shotResult = players.get(opposingPlayer).receiveShot(shot);

            // The opposingPlayer returns a ShotResult which is then passed to the currentPlayer's TargetGrid
            players.get(currentPlayer).receiveShotResult(shotResult); 

            // The results are printed to a 'neutral screen' where both players can see the result
            clear(); // Clear the screen
            String currentPlayerName = this.players.get(currentPlayer).getName();
            String opposingPlayerName = this.players.get(opposingPlayer).getName();
            String resultOfShot = shotResult.toString();
            String targetedCoord = shot.toString();
            //TODO: Get/Display "sunk" results
            System.out.println("Player " + currentPlayerName + " Fired at " + targetedCoord + "!\nand " + resultOfShot + "!"); // Spruce up in later iteration!

            // After the loop, the currentPlayer & opposingPlayer are toggled/swapped, so it's the next person's turn & it starts again.
        }
        currentPlayer = (currentPlayer == 0)? 1:0;
        opposingPlayer = (opposingPlayer == 1)? 0:1;

        // THIS WILL BE ITS OWN THING
        // Ask the player if they want to play the game again
        //boolean playAgain = yesOrNoPrompt("Play again?");
    }


    // Configure settings for the game overall
    public void config(){

        // Ask the player if they want a tutorial
        boolean tutorialOption = yesOrNoPrompt("Would you like to view a tutorial?");
        if (tutorialOption == true){
            tutorialOption(); 
        }

        // Configure the players
        configPlayers(); 
    }

    // PRIVATE METHODS--------------------

    // Allow the player to enter their name and decide whether they will play against a human or a CPU
    private void configPlayers(){
        // Get whether Player 2 is a human or an AI
        String playerNumberResponse;
        while (true){
            playerNumberResponse = ConsoleHelper.getInput("How many (human) players? Type 1/2, press [Enter]: ");
            if (playerNumberResponse.equals("1") || playerNumberResponse.equals("2")){
                break;
            } else {
                System.out.println(playerNumberResponse + " is an invalid number of players! Please try again...");
                continue;
            }
        }

        // Get Player 1's name
        String player1Name = ConsoleHelper.getInput("Player 1! Enter your name: ");
        Player player1 = new Player(player1Name);
        this.players.add(player1);

        if (playerNumberResponse == "1"){ // One player, one AI
            // Create an AI player
            //AI aiPlayer = new AI("AI NAME GOES HERE");
            //this.players.add(aiPlayer); // Is this allowed? Does it count as inheritance? 
        } else if (playerNumberResponse == "2"){ // Two players
            // Get Player 2's name (if applicable)
            String player2Name = ConsoleHelper.getInput("Player 2! Enter your name: ");
            Player player2 = new Player(player2Name);
            this.players.add(player2);
        }

        //TODO: Allow the player to set the AI's difficulty (if possible)
    }

    // Shows a tutorial on how to play the game
    private void tutorialOption(){
        //Print out... like... tutorial stuff, man
        System.out.println("Tutorial under construction, sorry!");
    }

    // Separate comma-delimited list of names (if necessary)
    private void formatNames(){
        
    }

    // Clear the screen!
    private void clear(){
        System.out.print("\033[H\033[2J");
    }

    // Give the player a yes/no prompt and process their answer
    private boolean yesOrNoPrompt(String prompt){
        // Assemble a list of valid "yes" responses
        ArrayList<String> validYesses = new ArrayList<String>();
        validYesses.add("y");
        validYesses.add("yes");
        validYesses.add("yup");
        validYesses.add("ok");

        // Get the player's response
        String response = ConsoleHelper.getInput(prompt + " Type y/n and press [Enter]: ");

        // Check if their response is a yes
        if (validYesses.contains(response.toLowerCase())){
            return true;
        } else {
            return false;
        }
        
    }
}
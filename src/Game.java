import java.util.List;
import java.util.ArrayList;

public class Game {

    // INSTANCE VARIBLES--------------------

    List<Player> players; // The players participating in this game
    Player currentPlayer; // The player currently making decisions
    Player opposingPlayer; // The player opposing the one making decisions

    // PUBLIC METHODS--------------------

    // Get the game moving 
    public void play(){

        // Game Loop
        while (true){
        
            config(); // Configure the settings of this game before jumping into it

            // Turns Loop
            while (true){

                // Iterate through each player, allowing them to take a turn
                for(Player player : players){

                    // Get the current player! This feels hacky...
                    this.currentPlayer = player;
                    if (players.indexOf(player) == 0){
                        this.opposingPlayer = players.get(1);
                    } else {
                        this.opposingPlayer = players.get(0);
                    }

                    // Check to make sure the chosen 'currentPlayer' still has ships to sink.
                    if (this.currentPlayer.areShipsSunk() == false){ 

                        // The 'currentPlayer' then takes a turn...a.k.a. takes a shot.
                        Coord shot = this.currentPlayer.takeShot(); 

                        // That shot is returned, to be passed to the opposingPlayer to receive.
                        ShotResult shotResult = this.opposingPlayer.receiveShot(shot); 

                        // The opposingPlayer returns a ShotResult which is then passed to the currentPlayer's TargetGrid
                        this.currentPlayer.recieveResult(shotResult); 

                        // The results are printed to a 'neutral screen' where both players can see the result
                        clear(); // Clear the screen
                        String currentPlayerName = this.currentPlayer.getName();
                        String opposingPlayerName = this.opposingPlayer.getName();
                        String resultOfShot = shotResult.toString();
                        String targetedCoord = shot.toString();
                        //TODO: Get/Display "sunk" results
                        ConsoleHelper.getInput("Player " + currentPlayerName + " Fired at " + targetedCoord + "!\nand " + resultOfShot + "!"); // Spruce up in later iteration!

                        // After the loop, the currentPlayer & opposingPlayer are toggled/swapped, so it's the next person's turn & it starts again.
                    } else {
                        //If the player has no ships, the game is over. End it!
                        System.out.println(this.currentPlayer.getName() + " has no more ships!\n" + this.opposingPlayer.getName() + "WINS!!"); // This is a placeholder and should have more pizazz. 
                        break;
                    }
                }
            }

            // Ask the player if they want to play the game again
            boolean playAgain = yesOrNoPrompt("Play again?");
            if (playAgain == true){
                continue;
            } else {
                break;
            }

        }
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
            AI aiPlayer = new AI("AI NAME GOES HERE");
            this.players.add(aiPlayer); // Is this allowed? Does it count as inheritance? 
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
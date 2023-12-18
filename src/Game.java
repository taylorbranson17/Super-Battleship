import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import Common.*;
import EasyAI.EasyAI;
import Theresa.Theresa;
import Accountant.TheAccountant;

public class Game {

    private List<PlayerInterface> players = new ArrayList<PlayerInterface>();
    private int currentPlayer = 0;
    private int opposingPlayer = 1;

    public Game() {
        Constants.clear();
        System.out.println(
                "\n\nWelcome to...\n" + Constants.asciiBattleship + "\nMay your shots be true, and your nerves hard as steel!\n\n");
        ConsoleHelper.getInput("Let's get your game settings configured, hit enter to continue >");
        Constants.clear();
        tutorialOption();
        Constants.clear();
        configPlayers();
    }

    public void play() {
        System.out.println("Welcome to Battleship- let the game begin!");
        System.out.println("Let's get the shots a-shootin...");
        ConsoleHelper
                .getInput("\nIt's " + players.get(currentPlayer).getName() + "'s turn. Hit enter to begin the game >");
        while (!players.get(currentPlayer).allShipsSunk()) {
            Coord shot = players.get(currentPlayer).takeTurn();
            Constants.clear();
            ShotResult result = players.get(opposingPlayer).receiveShot(shot);
            players.get(currentPlayer).receiveShotResult(result);
            if (result == ShotResult.SUNK) {
                System.out.println(players.get(currentPlayer).getName() + " took a shot on '" + shot.toString()
                        + "' and....\n" + result.toString() + "   \n" + players.get(opposingPlayer).getName() + "'s  "
                        + result.getShip()+"!");
            } else {
                System.out.println(players.get(currentPlayer).getName() + "'s shot on '" + shot.toString()
                        + "' was a....");
                System.out.println(result.toString());
            }
            ConsoleHelper.getInput("\n\nHit enter to begin " + players.get(opposingPlayer).getName() + "'s turn...");
            Constants.clear();
            currentPlayer = (currentPlayer == 0) ? 1 : 0;
            opposingPlayer = (currentPlayer == 1) ? 0 : 1;
        }
        System.out.println(players.get(opposingPlayer).getName() + " defeated " + players.get(currentPlayer).getName()
                + "'s fleet!\n\n" + Constants.asciiGameOver + "\n\n");
        try{
            Thread.sleep(3000);
        } catch (Exception e){

        }
        replay();
    }

    public void configPlayers() {
        players.clear();
        int num = ConsoleHelper.getInputBetween(
                "Please choose who you would like to play:\n1) Another human\n2) The Accountant AI (Hard)\n3) Theresa AI (Medium)\n4) John Panell AI (Easy)",
                0, 5, "Not a valid choice, please try again."); // back to zero index.
        Constants.clear();
        String message = "\n\n\nYou're ships have been placed.";
        switch (num) {
            case 1 -> message += TwoPlayers();
            default -> message += PlayerAI(num);
        }
        Constants.clear();
        System.out.println(message);
        try {
            Thread.sleep(6000);
        } catch (Exception e) {

        }
        Constants.clear();
    }

    private List<String> formatNames(String nameString) {
        return Arrays.asList(nameString.split(","))
                .stream()
                .map(name -> name.trim().toLowerCase())
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .collect(Collectors.toList());
    }

    private void tutorialOption() {
        int tutorialDecis = ConsoleHelper.getInputBetween(
                "\n\nWould you like to go through our tutorial before beginning?\n1) Yes\n2) No", 0, 3,
                "Please enter 1 for 'Yes', or 2 for 'No' to continue.");
        if (tutorialDecis == 1) {
            new Tutorial();
        }
    }

    private void replay() {
        int replayDecis = ConsoleHelper.getInputBetween(
                "Would you like to play again?\n1) Yes\n2) NO WAY\n3) I'm too ashamed...", 0, 4, "Not a valid option.");
        if (replayDecis == 1) {
            int replayType = ConsoleHelper.getInputBetween(
                    "\n\nHow would you like to continue:\n1) Same Players\n2) New Players", 0, 3, "Not a valid option.");
            Constants.clear();
            switch (replayType) {
                case 1:
                    players.forEach(player -> {
                        player.reset();
                        player.placeShips();
                        Constants.clear();
                    });
                    break;
                case 2:
                    configPlayers();
                    break;
            }
            play();
        } else if (replayDecis == 2) {
            Constants.clear();
            System.out.println("Thanks for playing! We'll see ya next time!");
        } else {
            Constants.clear();
            System.out.println("Whatever, you wimp. I hope you don't treat all of life like this...get outta here!");
        }
    }

    private String PlayerAI(int num) {
        String value = ConsoleHelper.getForceInput("Please enter your name: ");
        List<String> name = formatNames(value);
        players.add(new Player(name.get(0)));
        ConsoleHelper.getInput("You will now choose how to place your ships. Hit enter when you're ready >");
        Constants.clear();
        PlayerInterface opponent;
        String message;
        switch (num) {
            case 2:
                opponent = new TheAccountant();
                message = "\n\nPlease wait while we wake The Accountant...";
                break;
            case 3:
                opponent = new Theresa();
                message = "\n\nPlease wait while we wake Theresa...";
                break;
            default:
                opponent = new EasyAI();
                message = "\n\nPlease wait while we wake John Pannell...";
                break;
        }
        players.add(opponent);
        players.forEach(player->player.placeShips());
        return message;
    }

    private String TwoPlayers() {
        String value = ConsoleHelper.getForceInput("Please enter the names of the players, seperated by a comma: ");
        List<String> names = formatNames(value);
        String p1Name = names.get(0);
        String p2Name = names.get(1);
        players.add(new Player(names.get(0)));
        players.add(new Player(names.get(1)));
        ConsoleHelper.getInput("You will now choose how to place your ships. " + p1Name
                + " will begin. Hit enter when you're ready, " + p1Name + " >");
        Constants.clear();
        players.get(0).placeShips();
        Constants.clear();
        ConsoleHelper.getInput("It's now " + p2Name + "'s turn. Hit enter when you're ready, " + p2Name + " >");
        Constants.clear();
        players.get(1).placeShips();
        Constants.clear();
        return "\n\nPlease wait while we initialize your game...";
    }
}

public class Game {
    public static void main(String[] args) {
        Player humanPlayer = setupHumanPlayer();
        Player computerPlayer = setupComputerPlayer();
    }

    private static Player setupHumanPlayer() {
        return new Player("Your Name");
    }

    private static Player setupComputerPlayer() {
        return new Player("Computer");
    }
}

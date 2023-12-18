package Common;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class Constants {
    public static final Map<String, Integer> ShipMap = Map.of("Battleship", 4, "Carrier", 5, "Destroyer", 3, "Submarine", 3,
            "Patrol Boat", 2);

    private static final String hitString = " _   _ ___ _____ \r\n| | | |_ _|_   _|\r\n| |_| || |  | |  \r\n|  _  || |  | |  \r\n|_| |_|___| |_|  ";
    private static final String sunkString = " ____  _   _ _   _ _  __\r\n/ ___|| | | | \\ | | |/ /\r\n\\___ \\| | | |  \\| | ' / \r\n ___) | |_| | |\\  | . \\ \r\n|____/ \\___/|_| \\_|_|\\_\\";
    private static final String missString = " __  __ ___ ____ ____  \r\n|  \\/  |_ _/ ___/ ___| \r\n| |\\/| || |\\___ \\___ \\ \r\n| |  | || | ___) |__) |\r\n|_|  |_|___|____/____/ ";
    private static final String exclaim = " _ \r\n| |\r\n| |\r\n|_|\r\n(_)";
    public static final String asciiBattleship = " ____        _   _   _           _     _       \r\n| __ )  __ _| |_| |_| | ___  ___| |__ (_)_ __  \r\n|  _ \\ / _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\ \r\n| |_) | (_| | |_| |_| |  __/\\__ \\ | | | | |_) |\r\n|____/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/ \r\n                                        |_|    ";
    public static final String asciiGameOver = "  ____                         ___                 \r\n / ___| __ _ _ __ ___   ___   / _ \\__   _____ _ __ \r\n| |  _ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__|\r\n| |_| | (_| | | | | | |  __/ | |_| |\\ V /  __/ |   \r\n \\____|\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|   ";

    public static String getASCII(ShotResult result){
        List<String> splitExclaim = Arrays.asList(exclaim.split("\r\n"));
        List<String> stringList = null;
        switch(result){
            case HIT-> stringList = Arrays.asList(hitString.split("\r\n"));
            case MISS-> stringList = Arrays.asList(missString.split("\r\n"));
            case SUNK-> stringList = Arrays.asList(sunkString.split("\r\n"));
        }
        String message = "";
        for(int i = 0; i < 5; i++){
            message += stringList.get(i).concat(splitExclaim.get(i) + "\r\n");
        }
        return message;
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
    }
}

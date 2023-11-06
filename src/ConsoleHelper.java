import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String getInput(final String prompt) {
        String inStr = "";
        System.out.print(prompt);
        try {
            inStr = reader.readLine();
        } catch (final IOException e) {
            System.out.println("Error reading from user");
        }
        return inStr;
    }

    public static int getInputBetween(final String prompt, int min, int max, final String warning) {
        while (true) {
            System.out.print(prompt + "\nChoice: ");
            try {
                String inStr = reader.readLine();
                int decis = Integer.parseInt(inStr);
                if(decis > min && decis < max ){
                    return decis;
                }
            } catch (final IOException e) {
                System.out.println(warning);
            }
        }
    }
}

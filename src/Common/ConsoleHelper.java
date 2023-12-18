package Common;
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

    public static int getInputBetween(final String prompt, int min_exclusive, int max_exclusive, final String warning) {
        while (true) {
            System.out.print(prompt + "\n\nChoice: ");
            try {
                String inStr = reader.readLine();
                int decis = Integer.parseInt(inStr);
                if(decis > min_exclusive && decis < max_exclusive ){
                    return decis;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(warning + "\n");
            }
        }
    }

    public static String getForceInput(final String prompt){
       while(true){
            String inStr = "";
            System.out.print(prompt);
            try {
                inStr = reader.readLine();
                if(inStr.equals("")){
                    throw new IOException();
                } else {
                    return inStr;
                }
            } catch (final IOException e) {
                System.out.println("Input cannot be evaluated. Please type something.");
            }
       }
    }
}

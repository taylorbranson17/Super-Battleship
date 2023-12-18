package Accountant;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVPrinter {
    
    private String fileName;

    public CSVPrinter(String filename){
        this.fileName = filename;
    }

    //formats each String array into a single line.
    private String convertCellsToCSV(String[] data){
        return Stream.of(data).collect(Collectors.joining(","));
    }

    //complete conversion and printing to CSV file
    public void whenGivenDataString_CreateAndOutputFile(List<String[]> dataLines) throws IOException{
        //***swap out path for wherever you want the file to end up ***/
        File csvOutputFile = new File("C:\\Users\\taylo\\OneDrive\\Fall 2023\\CSCI_223- Soft. Dev\\Battleship\\Battleship (Core)\\src\\Accountant\\" + this.fileName);
        try(PrintWriter pw = new PrintWriter(csvOutputFile)){
            dataLines.stream().map(this::convertCellsToCSV).forEach(pw::println);
        } catch (Exception e){
            System.out.println("An error occured.");
        }
    }
}

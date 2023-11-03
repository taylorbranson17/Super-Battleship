import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualShipFactory extends ShipFactory {

    private Scanner scanner;

    public ManualShipFactory() {
        scanner = new Scanner(System.in);
    }

    public List<Ship> manualPlacement() throws Exception {
        List<Ship> manualShips = genShips(shipNames);

        for (Ship ship : manualShips) {
            while (true) {
                System.out.println("Enter the starting coordinate (e.g., A1):");
                String input = scanner.nextLine();
                Coord startCoord = parseCoordinate(input);

                if (!validateLeadCoord(startCoord)) {
                    System.out.println("Invalid starting coordinate. Try again.");
                    continue;
                }

                if (!filterOverlap(startCoord, totalShipCoords)) {
                    System.out.println("Starting coordinate overlaps with another ship. Try again.");
                    continue;
                }

                System.out.println("Enter the direction (N for North, E for East, S for South, W for West):");
                String directionInput = scanner.nextLine();
                Direction direction = parseDirection(directionInput);

                if (direction == null) {
                    System.out.println("Invalid direction. Try again.");
                    continue;
                }

                ArrayList<Coord> shipCoords = genShipCoords(startCoord, ship.getLength(), direction);

                if (!filterOverlap(shipCoords, totalShipCoords)) {
                    System.out.println("Coordinates overlap with another ship. Try again.");
                    continue;
                }

                totalShipCoords.addAll(shipCoords);
                ship.setCoords(shipCoords);
                break;
            }
        }

        return manualShips;
    }


    private Coord parseCoordinate(String input) {
        int row = 0; 
        int column = 0; 

        return new Coord(column, row);
    }

    private Direction parseDirection(String input) {
       
        if (input.equalsIgnoreCase("N")) {
            return Direction.N;
        } else if (input.equalsIgnoreCase("E")) {
            return Direction.E;
        } else if (input.equalsIgnoreCase("S")) {
            return Direction.S;
        } else if (input.equalsIgnoreCase("W")) {
            return Direction.W;
        } else {
            return null; // Invalid direction
        }
    }
}

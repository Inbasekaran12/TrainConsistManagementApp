import java.util.ArrayList;
import java.util.List;

/**
 * Use Case 2: Add Passenger Bogies to Train
 * Demonstrates CRUD operations using ArrayList
 *
 * @author Inbasekaran
 * @version 2.0
 */

public class UseCase2TrainConsist {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" UC2: Add Passenger Bogies to Train ");
        System.out.println("=====================================\n");

        // Create ArrayList to hold passenger bogies
        List<String> passengerBogies = new ArrayList<>();

        // CREATE - Add bogies
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        System.out.println("After Adding Bogies:");
        System.out.println("Passenger Bogies: " + passengerBogies);

        // DELETE - Remove a bogie
        passengerBogies.remove("AC Chair");

        System.out.println("\nAfter Removing 'AC Chair':");
        System.out.println("Passenger Bogies: " + passengerBogies);

        // READ - Check existence
        System.out.println("\nChecking if 'Sleeper' exists:");
        System.out.println("Contains Sleeper? " + passengerBogies.contains("Sleeper"));

        // Final state
        System.out.println("\nFinal Train Passenger Consist:");
        System.out.println(passengerBogies);

        System.out.println("\nUC2 operations completed successfully...");
    }
}

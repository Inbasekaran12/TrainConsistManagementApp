
import java.util.LinkedList;
import java.util.List;

/**
 * Use Case 4: Maintain Ordered Bogie Consist
 * Demonstrates ordered operations using LinkedList
 *
 * @author Developer
 * @version 4.0
 */

public class UseCase4TrainConsist {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" UC4: Maintain Ordered Bogie Consist ");
        System.out.println("=====================================\n");

        // Create a LinkedList
        List<String> trainConsist = new LinkedList<>();

        // Add bogies in sequence
        trainConsist.add("Engine");
        trainConsist.add("Sleeper");
        trainConsist.add("AC");
        trainConsist.add("Cargo");
        trainConsist.add("Guard");

        System.out.println("Initial Train Consist:");
        System.out.println(trainConsist);

        // Insert Pantry Car at position 2
        trainConsist.add(2, "Pantry Car");

        System.out.println("\nAfter Inserting 'Pantry Car' at position 2:");
        System.out.println(trainConsist);

        // Remove first and last bogie
        ((LinkedList<String>) trainConsist).removeFirst();
        ((LinkedList<String>) trainConsist).removeLast();

        System.out.println("\nAfter Removing First and Last Bogie:");
        System.out.println(trainConsist);

        System.out.println("\nUC4 ordered consist operations completed...");
    }
}
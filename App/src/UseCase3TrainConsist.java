import java.util.HashSet;
import java.util.Set;

/**
 * Use Case 3: Track Unique Bogie IDs
 * Demonstrates uniqueness using HashSet
 *
 * @author Inbasekaran
 * @version 3.0
 */

public class UseCase3TrainConsist {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" UC3: Track Unique Bogie IDs ");
        System.out.println("=====================================\n");

        // Create a Set to store unique bogie IDs
        Set<String> bogies = new HashSet<>();

        // ADD IDs (including duplicates)
        bogies.add("BG101");
        bogies.add("BG102");
        bogies.add("BG103");
        bogies.add("BG104");

        // Duplicate entries (will be ignored)
        bogies.add("BG101");
        bogies.add("BG102");

        // Display result
        System.out.println("Bogie IDs After Insertion:");
        System.out.println(bogies);

        System.out.println("\nNote: Duplicates are automatically ignored by HashSet.");
        System.out.println("UC3 uniqueness validation completed...");
    }
}
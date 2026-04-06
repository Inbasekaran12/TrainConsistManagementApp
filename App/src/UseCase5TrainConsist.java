import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Use Case 5: Preserve Insertion Order of Bogies
 * Demonstrates ordered uniqueness using LinkedHashSet
 *
 * @author Inbasekaran
 * @version 5.0
 */

public class UseCase5TrainConsist {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" UC5: Preserve Insertion Order of Bogies ");
        System.out.println("=====================================\n");

        // LinkedHashSet preserves order and avoids duplicates
        Set<String> formation = new LinkedHashSet<>();

        // Add bogies
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");

        // Attempt duplicate
        formation.add("Sleeper");

        // Display final formation
        System.out.println("Final Train Formation:");
        System.out.println(formation);

        System.out.println("\nNote: LinkedHashSet preserves insertion order and removes duplicates automatically.");
        System.out.println("UC5 formation setup completed...");
    }
}
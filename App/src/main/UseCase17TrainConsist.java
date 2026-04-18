import java.util.Arrays;

public class UseCase17TrainConsist {

    // Reusable method for test cases
    public static void sortBogieNames(String[] bogieNames) {
        Arrays.sort(bogieNames);
    }

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println(" UC17 Sort Bogie Names Using Arrays.sort()");
        System.out.println("==========================================\n");

        // Create array
        String[] bogieNames = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};

        // Display original
        System.out.println("Original Bogie Names:");
        System.out.println(Arrays.toString(bogieNames));

        // Sort using built-in method
        sortBogieNames(bogieNames);

        // Display sorted
        System.out.println("\nSorted Bogie Names (Alphabetical):");
        System.out.println(Arrays.toString(bogieNames));

        System.out.println("\nUC17 sorting completed...");
    }
}
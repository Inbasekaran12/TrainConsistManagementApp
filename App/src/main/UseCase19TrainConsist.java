import java.util.Arrays;

public class UseCase19TrainConsist {

    // Reusable Binary Search method
    public static boolean binarySearch(String[] bogieIds, String key) {

        // Precondition: sort before binary search
        Arrays.sort(bogieIds);

        int low = 0;
        int high = bogieIds.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int comparison = key.compareTo(bogieIds[mid]);

            if (comparison == 0) {
                return true; // found
            } else if (comparison < 0) {
                high = mid - 1; // search left
            } else {
                low = mid + 1; // search right
            }
        }

        return false; // not found
    }

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" UC19 Binary Search for Bogie ID ");
        System.out.println("=======================================\n");

        // Create array (can be unsorted)
        String[] bogieIds = {"BG309", "BG101", "BG550", "BG205", "BG412"};

        // Search key
        String key = "BG309";

        // Sort before displaying
        Arrays.sort(bogieIds);

        System.out.println("Sorted Bogie IDs:");
        for (String id : bogieIds) {
            System.out.println(id);
        }

        boolean found = binarySearch(bogieIds, key);

        if (found) {
            System.out.println("\nBogie " + key + " found using Binary Search.");
        } else {
            System.out.println("\nBogie " + key + " NOT found.");
        }

        System.out.println("\nUC19 search completed...");
    }
}
public class UseCase18TrainConsist {

    // Reusable method for test cases
    public static boolean linearSearch(String[] bogieIds, String searchId) {
        for (String id : bogieIds) {
            if (id.equals(searchId)) {
                return true; // found
            }
        }
        return false; // not found
    }

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" UC18 Linear Search for Bogie ID ");
        System.out.println("=======================================\n");

        // Create array of bogie IDs
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};

        // Search key
        String searchId = "BG309";

        // Display all bogies
        System.out.println("Available Bogie IDs:");
        for (String id : bogieIds) {
            System.out.println(id);
        }

        // Perform search
        boolean found = linearSearch(bogieIds, searchId);

        // Display result
        if (found) {
            System.out.println("\nBogie " + searchId + " found in train consist.");
        } else {
            System.out.println("\nBogie " + searchId + " NOT found.");
        }

        System.out.println("\nUC18 search completed...");
    }
}
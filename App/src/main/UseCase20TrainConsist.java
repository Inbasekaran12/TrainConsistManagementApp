public class UseCase20TrainConsist {

    // Search method with fail-fast validation
    public static boolean searchBogie(String[] bogieIds, String key) {

        // FAIL-FAST VALIDATION
        if (bogieIds == null || bogieIds.length == 0) {
            throw new IllegalStateException("No bogies available in train. Cannot perform search.");
        }

        // LINEAR SEARCH
        for (String id : bogieIds) {
            if (id.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println(" UC20 Exception Handling During Search ");
        System.out.println("====================================");

        // Empty train scenario
        String[] bogieIds = {};

        String searchId = "BG101";

        try {
            boolean found = searchBogie(bogieIds, searchId);

            if (found) {
                System.out.println("Bogie " + searchId + " found.");
            } else {
                System.out.println("Bogie " + searchId + " NOT found.");
            }

        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nUC20 execution completed...");
    }
}
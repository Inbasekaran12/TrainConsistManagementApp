import java.util.ArrayList;
import java.util.List;

/**
 * Main Class: TrainConsistApp
 * Use Case 1: Initialize Train and Display Consist Summary
 *
 * Description:
 * This class represents the entry point of the Train Consist Management Application.
 * It initializes an empty train consist using a dynamic list and displays
 * the initial state of the train.
 *
 * @author Inbasekaran
 * @version 1.0
 */

public class TrainConsistApp {

    public static void main(String[] args) {

        // Display welcome banner
        System.out.println("=====================================");
        System.out.println("  Train Consist Management App");
        System.out.println("=====================================\n");

        // Create a dynamic List to store train bogies
        List<String> trainConsist = new ArrayList<>();

        // Display initial consist information
        System.out.println("Train initialized successfully...");
        System.out.println("Initial Bogie Count: " + trainConsist.size());
        System.out.println("Current Train Consist: " + trainConsist);
        System.out.println("\nSystem ready for operations...");
    }
}
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;

// Class represent Ticketing system with configuration
class ticketSystem {
    @SerializedName("totalTickets") // annotation to map JSON property
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private String eventName;
    private BigDecimal ticketPrice;

    // constructor to initialize the ticket system
    public ticketSystem(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, String eventName, BigDecimal ticketPrice) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    // Getter methods for each field
    public int getTotalTickets(){
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
    public String getEventName(){
        return eventName;
    }
    public BigDecimal getTicketPrice(){
        return ticketPrice;
    }

    // override toString to provide a readable summary of the ticket system
    @Override
    public String toString(){
        return "Total Tickets: "+ totalTickets +
                "\nTicket Release Rate: "+ ticketReleaseRate +
                "\nCustomer Retrieval Rate: "+ customerRetrievalRate +
                "\nMax Ticket Capacity: "+ maxTicketCapacity +
                "\nEvent Name: "+ eventName +
                "\nTicket Price: "+ ticketPrice +"\n";
    }
}class TicketSystem {
    @SerializedName("totalTickets") // annotation to map JSON property
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private String eventName;
    private BigDecimal ticketPrice;

    // constructor to initialize the ticket system
    public TicketSystem(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, String eventName, BigDecimal ticketPrice) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    // Getter methods for each field
    public int getTotalTickets(){
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
    public String getEventName(){
        return eventName;
    }
    public BigDecimal getTicketPrice(){
        return ticketPrice;
    }

    // override toString to provide a readable summary of the ticket system
    @Override
    public String toString(){
        return "Total Tickets: "+ totalTickets +
                "\nTicket Release Rate: "+ ticketReleaseRate +
                "\nCustomer Retrieval Rate: "+ customerRetrievalRate +
                "\nMax Ticket Capacity: "+ maxTicketCapacity +
                "\nEvent Name: "+ eventName +
                "\nTicket Price: "+ ticketPrice +"\n";
    }
}
// Main class for the Real Time Event Ticketing System
public class RealTimeEventTicketingSystem {

    private static final String FILE_NAME = "TicketingSystem.txt";  // File name for text configuration
    private static ticketSystem ticketSystem;  // Instance to store ticket system configuration
    private static final String JSON_FILE_NAME = "TicketingSystem.json";  // File name for JSON configuration

    // Entry point of the program
    public static void main(String[] args) {

        while (true) {
            displayPreviousHistory(); // Check if user wants to view past configurations
            runTicketManagementSystem(); // Run the system
            again(); // Ask if the user wants to restart
        }
    }

    // Prompt user to view previous ticket system configurations
    public static void displayPreviousHistory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want see previous history (yes/no): ");
        String answer = scanner.nextLine().trim().toLowerCase();
        if (answer.equals("yes")){
            loadPreviousHistory(); // Load and display past configurations
        } else {
            runTicketManagementSystem();
            again();
        }
    }

    // Core function to run the ticketing system
    public static void runTicketManagementSystem() {
        Scanner scanner = new Scanner(System.in);

        // Input configuration settings
        System.out.println("\n--------Real Time Event Ticketing System--------\n");
        int totalTickets = getIntInput(scanner, "Enter Total Tickets: ");
        int ticketReleaseRate = getIntInput(scanner, "Enter Ticket Release Rate: ");
        int customerRetrievalRate = getIntInput(scanner, "Enter Customer Retrieval Rate: ");
        int maxTicketCapacity = getIntInput(scanner, "Enter Maximum Ticket Capacity: ");

        // Ensure total tickets do not exceed maximum ticket capacity
        if (totalTickets > maxTicketCapacity ){
            System.out.println("TotalTicket is greater than maxTicketCapacity. Enter totalTicket again.");
            totalTickets = getIntInput(scanner,"Enter Total Tickets Again: ");
        }

        // Vendor Information
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("\nVendor Get Information Section\n");
        System.out.print("Enter Event Name: ");
        String eventName = scanner1.nextLine();

        // Input validation for ticket price
        BigDecimal ticketPrice = BigDecimal.ZERO;
        while (true) {
            try {
                System.out.print("Enter Ticket Price: ");
                ticketPrice = new BigDecimal(scanner1.nextLine());
                if (ticketPrice.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("Please enter a valid price: ");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner1.nextLine();
            }
        }

        // Create ticket system configuration
        ticketSystem = new ticketSystem(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, eventName, ticketPrice);

        // Initialize Ticket Pool
        TicketPool ticketPool = new TicketPool(maxTicketCapacity);

        // Create Vendor Threads
        Vendor[] vendors = new Vendor[totalTickets];
        Thread[] vendorThreads = new Thread[totalTickets];
        for (int i = 0; i < totalTickets; i++) {
            vendors[i] = new Vendor(1, ticketReleaseRate, ticketPool, eventName, ticketPrice);
            vendorThreads[i] = new Thread(vendors[i], "Vendor ID-" + i);
            vendorThreads[i].start();
        }
        Vendor[] vendor = new  Vendor[maxTicketCapacity];

        // Wait for Vendor threads to complete
        for (Thread thread : vendorThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Vendor thread interrupted.");
            }
        }

        // Create Customer Threads
        Customer[] customers = new Customer[totalTickets];
        Thread[] customerThreads = new Thread[totalTickets];
        for (int i = 0; i < totalTickets; i++) {
            customers[i] = new Customer(ticketPool, customerRetrievalRate, 1);
            customerThreads[i] = new Thread(customers[i], "Customer ID-" + i);
            customerThreads[i].start();
        }

        // Wait for Customer threads to complete
        for (Thread thread : customerThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Customer thread interrupted.");
            }
        }

        System.out.println("\nAll operations completed successfully.");
    }

    // Prompt the user to restart or exit the program
    public static void again() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nYou waant stop the program or start adding tickets again (start / stop): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (answer.equals("start")) {
                break; // Loop will restart main()
            } else if (answer.equals("stop")) {
                saveConfigurationToFile(); // Save configuration to file
                saveConfigurationToJson(); // Save configuration to JSON
                System.out.println("Exiting the program. Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Invalid input. Please enter 'start' or 'stop'.");
            }
        }
    }

    // Helper function to get valid integer input from the user
    private static int getIntInput(Scanner scanner, String prompt) {
        int value;
        while (true) {
            try {
                System.out.print(prompt);
                value = scanner.nextInt();
                if (value <= 0) {
                    System.out.println("Please enter a positive number.");
                } else {
                    return value;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid positive number.");
                scanner.nextLine();
            }
        }
    }
    // Text file saving
    private static void saveConfigurationToFile() {
        if (ticketSystem == null) {
            System.out.println("No configuration to save.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write("--- Real Time Event Ticketing System ---\n");
            writer.write(ticketSystem.toString());
            writer.newLine();
            System.out.println("Configuration saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the configuration: " + e.getMessage());
        }
    }

    //Text file previous history loading
    private static void loadPreviousHistory() {
        System.out.println("--- Previous Configuration History ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous history found.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    // Save to JSON
    private static <Gson> void saveConfigurationToJson() {
        if (ticketSystem == null) {
            System.out.println("No configuration to save.");
            return;
        }

        // Convert ticketSystem object to JSON
        Gson gson = (Gson) new GsonBuilder().setPrettyPrinting().create(); // For formatted JSON
        String json = ((com.google.gson.Gson) gson).toJson(ticketSystem);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(JSON_FILE_NAME))) {
            writer.write(json);
            System.out.println("Configuration saved to " + JSON_FILE_NAME);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the configuration: " + e.getMessage());
        }
    }
}


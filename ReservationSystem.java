import java.util.Scanner;

public class ReservationSystem{
   public static void main(String[] args) {// Start of Program
       Scanner scanner = new Scanner(System.in);
       Cinema cinema = new Cinema();
       int menu = 0;
       while (menu != 6) { // Loop for Menu
	        System.out.println("\n1 - Reserve Tickets"); // Print Menu Options
	        System.out.println("2 - Show Current Availability");
	        System.out.println("3 - Show Count of Availability");
	        System.out.println("4 - Search Ticket");
	        System.out.println("5 - Print All Tickets");
	        System.out.println("6 - Exit");
	
	        System.out.print("\nEnter your menu: ");
	        menu = scanner.nextInt(); // Reads Menu Input
	
	        switch (menu) {
	            case 1: // Reserve Tickets
	                System.out.println("Do you want the program to allocate seats automatically? (Y/N)"); //Asks if user wants their seat to be allocated for them
	                char allocationinput = scanner.next().toUpperCase().charAt(0); // Stores input
	                boolean allocationBoolean = allocationinput == 'Y'; // Converts boolean to true if answered 'Y'
	                System.out.print("Enter the number of seats to reserve: ");
	                int numberOfSeats = scanner.nextInt(); // Input for number of desired seats to be researved
	                Cinema.reserveTickets(numberOfSeats, allocationBoolean); // Calls reserveTickets function from Ticket.java
	                break;
	
	            case 2: // Display Ticket Options
	                Cinema.DisplaySeats(); // Displays seating for Cinema
	                break;
	          
	            case 3: // Display Amount of Tickets Available
	                Cinema.AvailableTickets(); // Displays amount of seats availble for reservation
	                break;
	        
	            case 4:
	                System.out.print("Enter Ticket ID to search: "); // Asks user to input their Ticket ID for review
	                int ticketId = scanner.nextInt(); // Input TicketID
	                Cinema.searchTicket(ticketId); // Searches for Ticket ID information and prints if available
	                break;
	        
	            case 5:
	                Cinema.printAllTickets(); // Prints all ticket information
	                break;
	         
	            case 6:
	                System.out.println("Ending program.");scanner.close(); // Ends program
	                break;
	        
	            default:
	                System.out.println("Please enter a valid option."); // When user inputs a number that doesnt exist in the menu options
	        }
       }
   }
}

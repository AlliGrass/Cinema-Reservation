import java.util.ArrayList;
import java.util.Scanner;
class Ticket {
   private static int ticketCount = 0; // Begin with 0 tickets
   private int ticketID; // Define ticketID as int
   private int numberOfSeats; // Define numberOfSeats as int
   private ArrayList<String> reservedSeats; // Array for reserved seats
   public Ticket(int numberOfSeats, ArrayList<String> reservedSeats) { // Defining variables for seat information
       this.ticketID = ++ticketCount; // Increases ticketCOunt by 1
       this.numberOfSeats = numberOfSeats;
       this.reservedSeats = reservedSeats;
   }
   public int getticketID() {
       return ticketID; // Returns ticketID to desired location
   }
   public int getNumberOfSeats() {
       return numberOfSeats; // Returns number of seats to desired location
   }
   public ArrayList<String> getReservedSeats() {
       return reservedSeats; // Returns seat number to desired location
   }
}
class Cinema {
   private static final int totalSeats = 80; // Final int for total amount of seats
   private static int availableSeats = totalSeats; // int for initial amount of available seats
   private static ArrayList<Ticket> ticketList = new ArrayList<>(); // ticket Information
   private static char[] seats; // Seating code
   public Cinema() { // Constructor for seats array
       initializeSeats();
   }
   private void initializeSeats() {
       int rows = 8; // Nuimber of rows
       seats = new char[rows * 10];
      
       for (int i = 0; i < seats.length; i++) { // Mark seats as available with '-'
           seats[i] = '-';
       }
   }
  
   public static void reserveTickets(int numberOfSeats, boolean autoAllocate) { // Determines if user asked for autoallocation or not
       if (autoAllocate) {
           reserveTicketsAuto(numberOfSeats); // Autoallocates seats
       } else {
           reserveTicketsManual(numberOfSeats); // Manually allocates seats
       }
   }
   private static void reserveTicketsAuto(int numberOfSeats) {
       ArrayList<String> reservedSeats = new ArrayList<>();
       int seatsToAllocate = numberOfSeats;
       int seatList = 0;  // Begins seat list with =0
      
       while (seatsToAllocate > 0 && seatList < totalSeats) { // Allocates seats one by one
           if (seats[seatList] == '-') {
               int row = seatList / 10;  // Calculate the row based on the seat List
               int seatNum = seatList % 10 + 1;  // Calculate the seat number
               String seat = RowList.values()[row] + String.valueOf(seatNum);
               reservedSeats.add(seat); // Adds seat to list of seats that are reserved
               seatsToAllocate--;
              
               seats[seatList] = 'X'; // Changes availability of seat
           }
           seatList++;
       }
       if (seatsToAllocate == 0) {
           Ticket newTicket = new Ticket(numberOfSeats, reservedSeats); // Creates ticket for reservation
           ticketList.add(newTicket);
           availableSeats -= numberOfSeats; // Total available seats after ticket reservation
           System.out.println("Ticket ID is: " + newTicket.getticketID()); // Prints ticket ID
           System.out.println("Reserved the following seats: " + reservedSeats);
       } else {
           System.out.println("Cannot reserve " + numberOfSeats + " seats together."); // Prints that seats couldn't be reserved
          
           int nextRowStartList = (seatList / 10) * 10;
           while (seatsToAllocate > 0 && nextRowStartList < totalSeats) {
               if (seats[nextRowStartList] == '-') {
                   int row = nextRowStartList / 10;
                   int seatNum = nextRowStartList % 10 + 1;
                   String seat = RowList.values()[row] + String.valueOf(seatNum);
                   reservedSeats.add(seat);
                   seatsToAllocate--;
                
                   seats[nextRowStartList] = 'X';
               }
               nextRowStartList++;
           }
           if (seatsToAllocate == 0) {
               Ticket newTicket = new Ticket(numberOfSeats, reservedSeats);
               ticketList.add(newTicket);
               availableSeats -= numberOfSeats;
               System.out.println("Tickets reserved successfully.Ticket ID is: " + newTicket.getticketID());
               System.out.println("Reserved the following seats: " + reservedSeats);
           } else {
               System.out.println("Cannot reserve " + numberOfSeats + " seats.");
           }
       }
   }
   private static void reserveTicketsManual(int numberOfSeats) {
       ArrayList<String> reservedSeats = new ArrayList<>();
       int seatsToAllocate = numberOfSeats;
       System.out.println("Please choose your seats:");
       while (seatsToAllocate > 0) {
          
       	DisplaySeats(); // Display available seats
          
           System.out.println("Enter the seat number for seat " + (numberOfSeats - seatsToAllocate + 1) + ":");
           Scanner scanner = new Scanner(System.in);
           String chosenSeat = scanner.next().toUpperCase();
          
           if (isValidSeat(chosenSeat) && seats[getSeatList(chosenSeat)] == '-') { // Check seat is available
               reservedSeats.add(chosenSeat);
               seatsToAllocate--;
             
               seats[getSeatList(chosenSeat)] = 'X';
           } else {
               System.out.println("Please choose an available seat.");
           }
           scanner.close();
       }
       Ticket newTicket = new Ticket(numberOfSeats, reservedSeats);
       ticketList.add(newTicket);
       availableSeats -= numberOfSeats;
       System.out.println("Ticket ID is: " + newTicket.getticketID());
   }
   private static boolean isValidSeat(String seat) { // Shares if seat is available
       return seat.matches("[A-H][1-9]|10");
   }
   private static int getSeatList(String seat) {
       int row = RowList.valueOf(seat.substring(0, 1)).ordinal();
       int seatNum = Integer.parseInt(seat.substring(1)) - 1;
       return row * 10 + seatNum;
   }
   
   public enum RowList { // Row enum
		A,
		B,
		C,
		D,
		E,
		F,
		G,
		H;
	}
	
	public static int rowAmount = 8; // Num of rows
	
	private static int[] rowNumbers = {1, 2, 3, 4, 5, 6, 7, 8}; // Equavalent for enum
	// Method to get the day for a given value
   public static RowList getRowValue(int value) { // Exchange num for Row Letter
       for (RowList row : RowList.values()) {
           if (rowNumbers[row.ordinal()] == value) {
               return row;
           }
       }
       return null; // Value not found
   }
   public static void DisplaySeats() { // Displays cinema seating
		System.out.println("*******************************************************************");
		for (int i = 0; i < rowAmount; i++) {
			System.out.println();
			int value = i+1;
	        RowList row = getRowValue(value); // Exchange Row Label
			
			System.out.print("Row "+ row +"|");
			for (int j = 0; j < 10; j++) {
				if (i == 0 || j < 9 || j == 9)
					System.out.print(" ");
				else if (i > 0 || i < 10 || j !=9)
					System.out.print(" ");
				if (i > 0 || i < 9 || j == 9)
					System.out.print(" ");
				System.out.print(String.valueOf(row) + (int)(j+1) + ":" + seats[i*10+j]); } //Seat label
		System.out.println();
		}
		System.out.println("*******************************************************************");
			
	}
   public static void AvailableTickets() { // Display Available Seats
       System.out.println("Available Seats: " + availableSeats);
   }
   public static void searchTicket(int ticketID) { // Display Ticket Details
       for (Ticket ticket : ticketList) {
           if (ticket.getticketID() == ticketID) {
               System.out.println("Ticket ID: " + ticket.getticketID());
               System.out.println("Number of Seats: " + ticket.getNumberOfSeats());
               System.out.println("Reserved Seats: " + ticket.getReservedSeats());
               return;
           }
       }
       System.out.println("Ticket not found with ID: " + ticketID);
   }
   public static void printAllTickets() { // Displays all ticket details
       System.out.println("All Reserved Tickets:");
       for (Ticket ticket : ticketList) {
           System.out.println("Ticket ID: " + ticket.getticketID());
           System.out.println("Number of Seats: " + ticket.getNumberOfSeats());
           System.out.println("Reserved Seats: " + ticket.getReservedSeats());
           System.out.println("------------------------");
       }
   }
}



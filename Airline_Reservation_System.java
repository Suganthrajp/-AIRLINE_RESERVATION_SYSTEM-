package airlinereservationsystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Airline {
// Flight Class
static class Flight {
private String flightNumber;
private String origin;
private String destination;
private int totalSeats;
private int availableSeats;
public Flight(String flightNumber, String origin, String destination, int totalSeats) {
this.flightNumber = flightNumber;
this.origin = origin;
this.destination = destination;
this.totalSeats = totalSeats;
this.availableSeats = totalSeats;
}
public boolean hasAvailableSeats() {
return availableSeats > 0;
}
public void bookSeat() {
if (hasAvailableSeats()) {
availableSeats--;
System.out.println("Seat booked successfully on flight: " + flightNumber);
} else {
System.out.println("No seats available on flight: " + flightNumber);
}
}
public void displayFlightDetails() {
System.out.println("Flight: " + flightNumber + " | From: " + origin + " | To: " + destination +
" | Available Seats: " + availableSeats + "/" + totalSeats);
}
public String getFlightNumber() {
return flightNumber;
}
}

20
// Passenger Class (Updated to include more information)
static class Passenger {
private String name;
private int age;
private String passportNumber;
private String contactNumber;
public Passenger(String name, int age, String passportNumber, String contactNumber) {
this.name = name;
this.age = age;
this.passportNumber = passportNumber;
this.contactNumber = contactNumber;
}
public void displayPassengerInfo() {
System.out.println("Passenger Name: " + name);
System.out.println("Age: " + age);
System.out.println("Passport Number: " + passportNumber);
System.out.println("Contact Number: " + contactNumber);
}
}
// Reservation Class
static class Reservation {
private String reservationId;
private Passenger passenger;
private Flight flight;
public Reservation(String reservationId, Passenger passenger, Flight flight) {
this.reservationId = reservationId;
this.passenger = passenger;
this.flight = flight;
flight.bookSeat(); // Booking the seat upon reservation creation
}
public void displayReservationDetails() {
System.out.println("\nReservation ID: " + reservationId);
passenger.displayPassengerInfo();
System.out.println("Flight Number: " + flight.getFlightNumber());
flight.displayFlightDetails(); // Display updated flight details with available seats
}
}
// Main Method
public static void main(String[] args) {
List<Flight> flights = new ArrayList<>();
List<Reservation> reservations = new ArrayList<>();
Scanner scanner = new Scanner(System.in);
// Adding Flights
flights.add(new Flight("AI101", "New York", "London", 100));
flights.add(new Flight("AI102", "Delhi", "Tokyo", 50));

21

while (true) {
// Displaying Menu
System.out.println("\n------ Airline Reservation System ------ ");
System.out.println("1. View Available Flights");
System.out.println("2. Book a Flight");
System.out.println("3. Exit");
System.out.print("Choose an option (1/2/3): ");
int option = scanner.nextInt();
scanner.nextLine(); // Consume newline character left by nextInt
if (option == 1) {
// Display Available Flights
System.out.println("\nAvailable Flights:");
for (Flight flight : flights) {
flight.displayFlightDetails();
}
}
else if (option == 2) {
// Asking user to choose a flight by flight number
System.out.print("\nEnter Flight Number to Book: ");
String selectedFlightNumber = scanner.nextLine();
// Find the selected flight
Flight selectedFlight = null;
for (Flight flight : flights) {
if (flight.getFlightNumber().equals(selectedFlightNumber)) {
selectedFlight = flight;
break;
}
}
if (selectedFlight == null) {
System.out.println("Flight with number " + selectedFlightNumber + " not found.");
} else {
// Collecting passenger details
System.out.println("\nEnter Passenger Information to Book the Flight:");
System.out.print("Enter Passenger Name: ");
String name = scanner.nextLine();
System.out.print("Enter Age: ");
int age = scanner.nextInt();
scanner.nextLine(); // Consume the newline character left by nextInt
System.out.print("Enter Passport Number: ");
String passportNumber = scanner.nextLine();
System.out.print("Enter Contact Number: ");
String contactNumber = scanner.nextLine();
// Creating a passenger object
Passenger passenger = new Passenger(name, age, passportNumber, contactNumber);
// Creating a reservation
Reservation reservation = new Reservation("RES" + (reservations.size() + 1), passenger,

selectedFlight);

22

reservations.add(reservation);
// Display Reservation Details
System.out.println("\nReservation Details:");
reservation.displayReservationDetails();
}
}
else if (option == 3) {
System.out.println("Exiting the system. Thank you!");
break; // Exit the loop and end the program
}
else {
System.out.println("Invalid option. Please choose again.");
}
}
// Close the scanner to avoid resource leak
scanner.close();
}
}

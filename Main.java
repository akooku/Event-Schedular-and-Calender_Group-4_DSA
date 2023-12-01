import jdk.internal.event.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import Calendar

public class Main {
    public static void main(String[] args) {
        OurCalendar calendar = new OurCalendar();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    \n   === Calendar App ===
                    +-----------------------------+
                    |  Option    |     Action     |
                    +-----------------------------+
                    |     1      |  Create Event  |
                    |     2      | Display Daily  |
                    |     3      | Display Monthly|
                    |     4      | Event Reminders|
                    |     5      | Modify Event   |
                    |     6      | Delete Event   |
                    |     7      |      Exit      |
                    +-----------------------------+
                    """);

            System.out.println("Choose an option (1-7): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1 -> {
                    System.out.println("\n=== Create Event ===");
                    System.out.print("Enter Event Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Event Description: ");
                    String description = scanner.nextLine();

                    Event dummy  =  getEventDetails(OurCalendar calendar)
                    dummy.setTitle(title);
                    dummy.setDescription(description);

                    calendar.createEvent(dummy.getTitle(),
                            dummy.getDescription(), dummy.getDate(),
                            dummy.getTime);
                }
                case 2 -> {
                    System.out.println("\n=== Display Daily Event ===");
                    System.out.print("Enter Year to Display: ");
                    year = scanner.nextInt();
                    System.out.print("Enter Month to Display: ");
                    month = scanner.nextInt();
                    System.out.print("Enter Day to Display: ");
                    day = scanner.nextInt();
                    calendar.displayDailyView(year, month, day)
                }
                case 3 -> {
                    System.out.println("\n=== Display Monthly View ===");
                    System.out.println("Enter Year: ");
                    year = scanner.nextInt();
                    System.out.println("Enter Mouth: ");
                    month = scanner.nextInt();
                    calendar.displayMonthlyView(year, month);
                }
                case 4 -> calendar.displayEventReminders();
                case 5 -> {
                    System.out.println("\n=== Modify Event ===");

                    Event dummy = getEventDetails();
                    calendar.retrieveEvents(dummy);

                }
                case 6 -> System.out.println("\n=== Delete Event ===");
                case 7 -> {
                    System.out.println("Exiting Calendar App. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }

        public Events getEventDetails( ){
            System.out.print("Enter Year of Event: ");
            int year = scanner.nextInt();

            System.out.print("Enter Month of Event: ");
            int month = scanner.nextInt();

            System.out.print("Enter Day of Event: ");
            int day = scanner.nextInt();

            System.out.print("Enter Time of Event (hh:mm): ");
            int hour = scanner.nextInt();
            int minute = scanner.nextInt();

            LocalTime time = LocalTime.of(hour, minute);
            LocalDate date = LocalDate.of(year, month, day);

            Event dummy = new Event("","" , LocalDate date, LocalTime time)

            return dummy;
        }


    }




}

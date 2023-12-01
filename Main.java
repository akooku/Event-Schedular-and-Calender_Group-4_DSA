import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        OurCalendar calendar = new OurCalendar();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    \n 
                    +-----------------------------+
                    |    === Calendar App ===     |
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
                    Event dummy = getEventDetails(scanner, calendar, false);
                    calendar.createEvent(dummy.getTitle(), dummy.getDescription(), dummy.getDate(), dummy.getTime());
                }
                case 2 -> {
                    System.out.println("\n=== Display Daily Event ===");

                    System.out.print("Enter Year of Event: ");
                    int year = scanner.nextInt();
                    System.out.print("Enter Month of Event: ");
                    int month = scanner.nextInt();
                    System.out.print("Enter Day of Event: ");
                    int day = scanner.nextInt();

                    LocalDate date = LocalDate.of(year, month, day);

                    Event dummy = new Event (null, null, date  ,null);
                    calendar.displayDailyView(dummy);
                }
                case 3 -> {
                    System.out.println("\n=== Display Monthly View ===");
                    System.out.print("Enter Year: ");
                    int year = scanner.nextInt();
                    System.out.print("Enter Month: ");
                    int month = scanner.nextInt();
                    calendar.displayMonthlyView(year, month);
                }
                case 4 -> calendar.displayEventReminders();
                case 5 -> {
                    System.out.println("\n=== Modify Event ===");
                    Event dummy = getEventDetails(scanner, calendar, true);
                    Event target = calendar.retrieveEvent(dummy);

                    System.out.println("\nSelected Event: ");
                    System.out.println(target);

                    dummy = target;
                    calendar.deleteEvent(target);

                    // Implement modify event logic using dummy
                    System.out.print("Enter new title (Press Enter to keep the current title): ");
                    String newTitle = scanner.nextLine().trim();
                    if (!newTitle.isEmpty()) {
                        dummy.setTitle(newTitle);
                    }

                    System.out.print("Enter new description (Press Enter to keep the current description): ");
                    String newDescription = scanner.nextLine().trim();
                    if (!newDescription.isEmpty()) {
                        dummy.setDescription(newDescription);
                    }

                    System.out.print("Enter new date in the format YYYY-MM-DD (Press Enter to keep the current date): ");
                    String newDateInput = scanner.nextLine().trim();
                    if (newDateInput.isEmpty()) {
                        LocalDate newDate = LocalDate.parse(newDateInput);
                        dummy.setDate(newDate);
                    }

                    System.out.print("Enter new time in the format HH:mm (Press Enter to keep the current time): ");
                    String newTimeInput = scanner.nextLine().trim();
                    if (newTimeInput.isEmpty()) {
                        LocalTime newTime = LocalTime.parse(newTimeInput);
                        dummy.setTime(newTime);
                    }

                    calendar.createEvent(dummy.getTitle(),
                        dummy.getDescription(),
                        dummy.getDate(),
                        dummy.getTime());

                }
                case 6 ->{
                    System.out.println("\n=== Delete Event ===\n");
                    Event dummy = getEventDetails(scanner, calendar, true);
                    calendar.deleteEvent(dummy);
                }
                 
                case 7 -> {
                    System.out.println("Exiting Calendar App. Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }

    public static Event getEventDetails(Scanner scanner, OurCalendar calendar, boolean partial) {
        String title = null;
        String description = null;

        if (!partial){
            System.out.print("Enter Event Title: ");
            title = scanner.nextLine();
            System.out.print("Enter Event Description: ");
            description = scanner.nextLine();
        }
    
        System.out.print("Enter Year of Event: ");
        int year = scanner.nextInt();
        System.out.print("Enter Month of Event: ");
        int month = scanner.nextInt();
        System.out.print("Enter Day of Event: ");
        int day = scanner.nextInt();
        System.out.print("Enter Hour of Event: ");
        int hour = scanner.nextInt();
        System.out.print("Enter Minute of Event: ");
        int minute = scanner.nextInt();

        scanner.nextLine(); // Consume the newline character

        LocalTime time = LocalTime.of(hour, minute);
        LocalDate date = LocalDate.of(year, month, day);
        if (partial){
            return new Event(null,null, date, time);
        }
        return new Event(title,description, date, time);

        
    }
}
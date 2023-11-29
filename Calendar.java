import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calendar {
    private final HashTable<HashTable<HashTable<List<Event>>>> calendar;

    public Calendar() {
        this.calendar = new HashTable<>();
    }

    public void createEvent(String title, String description, LocalDate date, LocalTime time) {
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        // Ensure the year level exists
       HashTable<HashTable<List<Event>>> yearLevel = calendar.retrieveVal(year);

        if (yearLevel == null) {
            yearLevel = new HashTable<>();
            calendar.insert(year, yearLevel);
        }

        // Ensure the month level exists
        HashTable<List<Event>> monthLevel = yearLevel.retrieveVal(month);
        if (monthLevel == null) {
            monthLevel = new HashTable<>();
            yearLevel.insert(month, monthLevel);
        }

        // Ensure the day level exists
        List<Event> dayEvents = monthLevel.retrieveVal(day);
        if (dayEvents == null) {
            dayEvents = new ArrayList<>();
            monthLevel.insert(day, dayEvents);
        }

        // Searching for possible Conflicts

        int conflict =  binarySearchConflict(dayEvents, time);

        if (conflict > 0 ) {
            System.out.println("Event conflict detected!\n There is already an event at the same time.");
            System.out.println("Do you want to ovewrite event  anyway? (y/n)");

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine().trim().toLowerCase();

            if (!userInput.equals("y")) {
                System.out.println("Event Overwritten.");
                dayEvents.remove(conflict);
            }else{
                System.out.println("Event not added due to conflict.");
                return;
            }
        }

        // Create and add the event
        Event newEvent = new Event(title, description, date, time);
        // Making insert based on time
        for (Event e : dayEvents){
            if (e != null){
                if(newEvent.getTime().isBefore(e.getTime())){
                    int index = dayEvents.indexOf(e);
                    dayEvents.add(index, newEvent);
                }
            }
        }
        System.out.println("Event created: " + newEvent +"\n");
    }

    private int binarySearchConflict(List<Event> events, LocalTime newEventTime) {
        int low = 0;
        int high = events.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            LocalTime midEventTime = events.get(mid).getTime();

            if (midEventTime.equals(newEventTime)) {

                return mid; // Conflict detected

            } else if (midEventTime.isBefore(newEventTime)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1; // No conflict
    }



}
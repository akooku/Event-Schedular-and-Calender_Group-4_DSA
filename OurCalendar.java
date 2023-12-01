import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class OurCalendar {
    public HashTable<HashTable<HashTable<List<Event>>>> calendar;

    public OurCalendar() {
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

    public int binarySearchConflict(List<Event> events, LocalTime newEventTime) {
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

    public void displayDailyView(Event target) {

        // Retrieve events for the specifies day
        List<Event> dayEvents = retrieveEvents(target);

        if (dayEvents != null && !dayEvents.isEmpty()) {
            System.out.println(target.getDate() + " Events: ");
            for (Event e : dayEvents) {
                System.out.println(e);
            }
        }
        else {
            System.out.println(target.getDate() + " has no events.");
        }
    }

    public void displayMonthlyView(int year, int month) {
        HashTable<HashTable<List<Event>>> yearLevel = calendar.retrieveVal(year);

        if (yearLevel != null) {
            HashTable<List<Event>> monthLevel = yearLevel.retrieveVal(month);

            if (monthLevel != null) {
                System.out.println("Year: " + year + ", Month: " + month);

                // Iterate through each day in the month using a hash table
                for (int day = 1; day <= 31; day++) {
                    List<Event> dayEvents = monthLevel.retrieveVal(day);

                    if (dayEvents != null && !dayEvents.isEmpty()) {
                        System.out.println("\n" + LocalDate.of(year, month, day) + " Events: ");
                        for (Event e : dayEvents) {
                            System.out.println(e);
                        }
                    }
                }
                return;
            }
        }
        System.out.println("No events found for the specified month and year.");
    }

    public List<Event> retrieveEvents(Event target) {
        int year = target.getDate().getYear();
        int month = target.getDate().getMonthValue();
        int day = target.getDate().getDayOfMonth();
        HashTable<HashTable<List<Event>>> yearLevel = calendar.retrieveVal(year);

        if (yearLevel != null) {
            HashTable<List<Event>> monthLevel = yearLevel.retrieveVal(month);

            if (monthLevel != null) {
                return monthLevel.retrieveVal(day);
            }
        }
        return null;     
    }

    public Event retrieveEvent(Event target) {
        List<Event> events = retrieveEvents(target);
        int index = binarySearchConflict(events, target.getTime());
        return events.get(index);
    }

    public void displayEventReminders() {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        boolean found = false;

        Event dummy =  new Event(null, null, currentDate, currentTime);

        List<Event> dayEvents = retrieveEvents(dummy); 

        for (Event upcomingEvent : dayEvents) {
            if (currentTime.isBefore(upcomingEvent.getTime())) {
                System.out.println("Next Upcoming Event...");
                System.out.println(upcomingEvent);
                found = true;
                break; // Exit the loop after displaying the first upcoming event
            }
        }

        if (!found) {
            System.out.println("No upcoming event found.");
        }

        
    }

    public void deleteEvent(Event target){
        List<Event> events  =  retrieveEvents(target);

        int index = binarySearchConflict(events,
                target.getTime());
        if (index>0) {
            events.remove(index);
        }
    }
}
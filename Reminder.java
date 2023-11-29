import java.time.LocalDateTime;
import java.util.PriorityQueue;

public class Reminder {
    PriorityQueue<Notice> reminders;

    public Reminder() {
        this.reminders = new PriorityQueue<>();
    }

    public void addReminder(Event event) {

        LocalDateTime eventDateTime = LocalDateTime.of(event.getDate(), event.getTime());

    }

    public void displayReminders() {
        System.out.println("\n--- Reminders ---");
        for (Notice notice : reminders) {
            System.out.println(notice);
        }
    }

    // Other methods for managing reminders as needed...
}


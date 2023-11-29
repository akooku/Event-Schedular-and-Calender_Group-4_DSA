import java.time.LocalDate;
import java.time.LocalTime;


public class Main {
    // Add methods for viewing events at different levels as needed...

    public static void main(String[] args) {
        Calendar threeLevelCalendar = new Calendar();

        // Example usage
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        threeLevelCalendar.createEvent("Meeting", "Discuss project", today, now.plusHours(1));
        threeLevelCalendar.createEvent("Lunch", "Team lunch", today, now.plusHours(4));
        threeLevelCalendar.createEvent("Gym", "Workout session", today.plusDays(1), LocalTime.of(9, 0));
    }


}

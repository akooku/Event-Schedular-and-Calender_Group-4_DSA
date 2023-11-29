import java.time.LocalDate;
import java.time.LocalTime;

public class Event {

    final int DEFAULT_PRIORITY = 1;

    private String title;
    private String description;
    private int priority;
    private LocalDate date;
    private LocalTime time;

    // Constructor
    public Event(String title, String description, LocalDate date, LocalTime time) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.priority = this.DEFAULT_PRIORITY;
    }
    // Overloaded method that includes priority
    public Event(String title, String description, LocalDate date, LocalTime time, int priority) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.priority = priority;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
    public int getPriority() {
        return priority;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    @Override
    public String toString() {
        return "Event: " + getTitle()
                + "\nDescription: " + getDescription()
                + "\nDate: " + getDate()
                + "\nTime: " + getTime();
    }
}

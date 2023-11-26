import java.time.LocalDate;
import java.time.LocalTime;

public class Event {

    private String title;
    private String description;
    private LocalDate date;
    private LocalTime time;

    public Event(String title, String descr, LocalDate day, LocalTime time) {
        this.title = title;
        this.description = descr;
        this.date = day;
        this.time = time;
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

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void getDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Event: " + getTitle()
        + ", Description: " + getDescription()
        + ", Date: " + getDate()
        + ", Time: " + getTime();
    }
}
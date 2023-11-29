import java.time.LocalDateTime;

class Notice{
    String eventName;
    LocalDateTime eventDateTime;
    int priority;

    public Notice(String eventName, LocalDateTime eventDateTime, int priority) {
        this.eventName = eventName;
        this.eventDateTime = eventDateTime;
        this.priority = priority;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public int getPriority() {
        return priority;
    }
    public int compareTo(Notice other) {
        return this.eventDateTime.compareTo(other.eventDateTime);
    }

    @Override
    public String toString() {
        return "Event: " + eventName + "\nTime: " + eventDateTime + "\n";
    }
}
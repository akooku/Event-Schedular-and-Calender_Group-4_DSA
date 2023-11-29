import java.util.Objects;

public class Entry <T, K>{
    K key;

    T value;

    // Constructor that take key as and ID ,and a value
    public Entry(K key, T value){
        this.key = key;
        this.value = value;
    }

    // Getter and Setter methods for the instance variables
    public void setKey( K key) {
        this.key = key;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public boolean equals(Entry< T , K >  o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return value == o.value && Objects.equals(key, o.key);
    }

}

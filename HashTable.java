import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HashTable<T> {
    // Load factor threshold for dynamic resizing
    private static final double LOAD_FACTOR_THRESHOLD = 0.7;
    private int size;
    private List<Entry<T>> table;

    // Constructor
    public HashTable(int size) {
        // Ensure the initial size is odd for proper double hashing
        if (isEven(size)) {
            size = resize(size);
        }

        // Find the next prime size
        while (!isPrime(size)) {
            size += 2;
        }

        // Initialize size and create a list for the table
        this.size = size;
        this.table = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            table.add(null);
        }
    }

    // Primary hash function
    private int primaryHash(int key) {
        return key % this.size;
    }

    // Secondary hash function
    private int secondaryHash(int key) {
        int hash = 0;
        int secondaryPrime = size - 2;

        // Finding the last prime number less than the size
        while (isPrime(secondaryPrime)) {
            secondaryPrime = secondaryPrime - 2;
            if (secondaryPrime < 0) {
                secondaryPrime = 31;
            }
        }
        return (secondaryPrime - (key % secondaryPrime));
    }

    // Double hash function using both primary and secondary hash functions
    public int doubleHash(int key, int i) {
        return (primaryHash(key) + i * secondaryHash(key)) % this.size;
    }

    // Insert method to add a key-value pair to the hash table
    public void insert(int key, T value) {
        if (isFull()) {
            rehash();
        }

        int i = 0;
        int index = doubleHash(key, i);

        // Find an available slot using double hashing
        while (table.get(index) != null) {
            if (Objects.equals(table.get(index).getKey(), key)) {
                break;
            } else {
                i++;
                index = doubleHash(key, i);
            }
        }

        // Insert the new key-value pair
        table.set(index, new Entry<>(key, value));
    }

    // Retrieve method to get an entry given a key
    public Entry<T> retrieve(int key) {
        int i = 0;
        int index = doubleHash(key, i);

        // Search for the entry using double hashing
        while (table.get(index) != null) {
            if (Objects.equals(table.get(index).getKey(), key)) {
                return table.get(index);
            }
            i++;
            index = doubleHash(key, i);
        }

        return null; // Return null if the entry is not found
    }

    // Delete method to remove an entry based on the key
    public boolean delete(int key) {
        int i = 0;
        int index = doubleHash(key, i);

        // Search for the entry using double hashing
        while (table.get(index) != null) {
            if (Objects.equals(table.get(index).getKey(), key)) {
                // Delete the entry and return true
                table.set(index, null);
                return true;
            }
            i++;
            index = doubleHash(key, i);
        }

        return false; // Return false if the entry is not found
    }

    // Check if a number is prime
    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Check if a number is even
    public boolean isEven(int n) {
        return n % 2 == 0;
    }

    // Increase the table size to the next odd number
    public int resize(int size) {
        return size + 2;
    }

    // Rehash method to migrate all items to a new table
    public void rehash() {
        int newSize = this.resize(this.size);

        // Find the next prime number
        while (!isPrime(newSize)) {
            newSize += 2;
        }

        // Save the old table and create a new one
        List<Entry<T>> oldTable = new ArrayList<>(table);
        this.size = newSize;
        this.table = new ArrayList<>(size);

        // Initialize the new table with null entries
        for (int i = 0; i < size; i++) {
            table.add(null);
        }

        // Reinsert all items from the old table
        for (Entry<T> entry : oldTable) {
            if (entry != null) {
                int i = 0;
                int index = doubleHash(entry.getKey(), i);

                // Find an available slot using double hashing
                while (table.get(index) != null) {
                    i++;
                    index = doubleHash(entry.getKey(), i);
                }

                // Insert the entry into the new table
                table.set(index, entry);
            }
        }
    }

    // Check if the table is full based on the load factor threshold
    public boolean isFull() {
        return size <= table.size() * LOAD_FACTOR_THRESHOLD;
    }

    // Getter for the table size
    public int getSize() {
        return size;
    }

    // Getter for the table
    public List<Entry<T>> getTable() {
        return table;
    }

    // Display the content of the hash table
    public void displayTable() {
        System.out.println("\n--- Hash Table Content ---");
        for (int i = 0; i < size; i++) {
            Entry<T> entry = table.get(i);
            if (entry != null) {
                System.out.println("\nIndex " + i + "\n" + entry.getKey() + ": " +
                        entry.getValue() + "\n-----------------------");
            }
        }
    }
}

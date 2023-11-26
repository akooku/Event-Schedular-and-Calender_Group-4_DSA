// Generic DoubleLinkedList class
class DoubleLinkedList<T> {
    // Head and tail of the doubly linked list
    private Node<T> head;
    private Node<T> tail;

    // Size of the doubly linked list
    private int size;

    // Constructor initializes an empty list
    public DoubleLinkedList() {
        head = tail = null;
        size = 0;
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Return the size of the list
    public int listSize() {
        return size;
    }

    // Return a node at a specific position
    public Node<T> getNode(int position) {
        if (position > size + 1 || position < 1) {
            return null; // Invalid position
        }

        Node<T> current;
        boolean getLeft = position <= Math.floor(size / 2.0);

        if (getLeft) {
            current = head;
            int i = 1;
            while (i < position) {
                current = current.next;
                i++;
            }
        } else {
            current = tail;
            int i = size;
            while (i > position) {
                current = current.previous;
                i--;
            }
        }

        return current;
    }

    // Insert a new node at a specific position
    public void insert(int position, T e) {
        if (position > size + 1 || position < 1) {
            throw new IllegalArgumentException("Insertion not possible: invalid position");
        }

        Node<T> newNode = new Node<>(e);

        if (position == 1) {
            newNode.next = head;
            if (head == null) {
                head = tail = newNode;
            } else {
                head.previous = newNode;
                head = newNode;
            }
        } else if (position == size + 1) {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        } else {
            boolean insertLeft = position <= Math.floor(size / 2.0);
            Node<T> current;
            int count;

            if (insertLeft) {
                current = head;
                count = 1;
                while (count != position - 1) {
                    current = current.next;
                    count++;
                }
                newNode.next = current.next;
                current.next.previous = newNode;
                newNode.previous = current;
                current.next = newNode;
            } else {
                current = tail;
                count = size;
                while (count != position + 1) {
                    current = current.previous;
                    count--;
                }
                newNode.previous = current.previous;
                current.previous.next = newNode;
                newNode.next = current;
                current.previous = newNode;
            }
        }
        size++;
    }

    // Delete a node at a specific position
    public void delete(int position) {
        if (position > size || position < 1) {
            throw new IllegalArgumentException("Deletion not possible: invalid position");
        }

        if (position == 1) {
            head = head.next;
            if (head == null) {
                tail = null;
            } else {
                head.previous = null;
            }
        } else if (position == size) {
            tail = tail.previous;
            tail.next = null;
        } else {
            boolean deleteLeft = position <= Math.floor(size / 2.0);
            int count;
            Node<T> current;

            if (deleteLeft) {
                current = head;
                count = 1;
                while (count != position - 1) {
                    current = current.next;
                    count++;
                }
                current.next = current.next.next;
                current.next.previous = current;
            } else {
                current = tail;
                count = size;
                while (count != position + 1) {
                    current = current.previous;
                    count--;
                }
                current.previous = current.previous.previous;
                current.previous.next = current;
            }
        }
        size--;
    }

    // Overloaded method to delete a node based on data
    public void delete(T e) {
        delete(positionOf(e));
    }

    // Find the position of a node based on given data
    public int positionOf(T e) {
        Node<T> target = new Node<>(e);
        int count = 1;
        Node<T> current = head;

        while (current != null && !current.data.equals(target.data)) {
            current = current.next;
            count++;
        }

        if (current == null) {
            throw new IllegalArgumentException("Element not found in the list");
        }

        return count;
    }

    // Display items in the current list
    public void display() {
        Node<T> current = head;

        while (current != null) {
            System.out.println(current.data.toString());
            current = current.next;
        }
    }
}

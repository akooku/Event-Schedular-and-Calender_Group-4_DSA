public class LinkedList {
    private Node head;

    public LinkedList() {
        this.head = null;
    }

    public void insert(Event event) {
        Node newNode = new Node(event);
        newNode.next = head;
        head = newNode;
    }

    // public void display() {
    //     Node current = head;
    //     System.out.println("Event List: ");
    //     while (current != null) {
    //         System.out.println(current.data);
    //         current = current.next;
    //     }
    // }
}

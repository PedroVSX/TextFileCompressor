package DataStructure.PriorityQueue;

public class Node <T> {
    T data;
    Integer priority;
    Node<T> previous;
    Node<T> next;

    public Node(T data, int priority) {
        this.data = data;
        this.priority = priority;
        this.previous = null;
        this.next = null;
    }

    public Node(T data) {
        this.data = data;
        this.priority = null;
        this.previous = null;
        this.next = null;
    }

    @Override
    public String toString() {
        return  "Data: " + data + " | " + "Priority: " + priority;
    }
}

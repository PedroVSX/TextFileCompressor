package DataStructure.PriorityQueue;

public class PriorityQueue <T extends Comparable<T>> {
    private Node<T> first;
    private Node<T> last;
    private int cont = 0;

    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);

        if (first == null) {
            first = newNode;
            last = newNode;

        } else {

            if (newNode.data.compareTo(first.data) < 0) {
                newNode.next = first;
                first.previous = newNode;
                first = newNode;

            } else if (newNode.data.compareTo(last.data) >= 0) {
                newNode.previous = last;
                last.next = newNode;
                last = newNode;

            } else {
                Node<T> aux = first;

                while (newNode.data.compareTo(aux.data) >= 0) {
                    aux = aux.next;
                }

                newNode.next = aux;
                aux.previous.next = newNode;
                newNode.previous = aux.previous;
                aux.previous = newNode;
            }

        }

        cont++;
    }

    public T dequeue() {
        T aux = front();

        if (cont >= 1) {
            first = first.next;
            cont--;
        } else {
            clear();
        }

        return aux;
    }

    public void clear() {
        first = null;
        last = null;
        cont = 0;
    }

    public int size() {
        return cont;
    }

    public T front() {
        return first.data;
    }

    public boolean empty() {
        return cont == 0;
    }

    public Node<T> get(int index) {
        Node<T> aux = first;

        for (int i = 0; i < cont; i++) {
            if (i == index) {
                return aux;
            }

            aux = aux.next;
        }

        return null;
    }

    public void show() {
        Node<T> aux = first;
        String concatener = "";

        for (int i = 0; i < cont; i++) {
            if (i == cont-1) {
                concatener += aux.data;
            } else {
                concatener += aux.data + " ";
                aux = aux.next;
            }
        }

        System.out.println("[" + concatener + "]");
    }
}

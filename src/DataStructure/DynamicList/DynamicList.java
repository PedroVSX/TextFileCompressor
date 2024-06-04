package DataStructure.DynamicList;

public class DynamicList {

    private Node first;
    private Node last;
    private int count = 0;

    public void add(Character elem) {
        Node novo = new Node(elem);
        Node aux;

        if (this.first == null){
            this.first = novo;
            this.last = novo;

        } else {
            last.next = novo;
            aux = last;
            last = novo;
            last.previous = aux;
        }

        count++;
    }

    public void add(int pos, Character elem) {
        Node novo = new Node(elem);
        Node aux = first;

        for (int i = 0; i < pos - 1; i++) {
            aux = aux.next;
        }

        novo.next = aux.next;
        aux.next.previous = novo;
        aux.next = novo;
        novo.previous = aux;

        count++;
    }

    public int size() {
        return count;
    }

    public void clear() {
        count = 0;
        first = null;
        last = null;
    }

    public boolean contains(Character obj) {
        Node aux = first;

        for (int i = 0; i < count; i++) {
            if (aux.data == obj) {
                return true;
            }
            aux = aux.next;
        }

        return false;
    }

    public void remove(Character obj) {
        Node aux = first;
        boolean found = false;

        if (first.data == obj) {
            first = first.next;
            found = true;

        } else if (last.data == obj) {
            last = last.previous;
            found = true;

        } else {
            for (int i = 1; i < count - 1; i++) {
                if (aux.data == obj) {
                    found = true;
                    break;
                }

                aux = aux.next;
            }

            aux.previous.next = aux.next;
            aux.next.previous = aux.previous;
        }

        if (found) {
            count--;
        }
    }

    public void remove(int pos) {
        Node aux = first;
        boolean found = false;

        if (pos == 0) {
            first = first.next;
            found = true;

        } else if (pos == count - 1) {
            last = last.previous;
            found = true;

        } else {
            for (int i = 1; i < count - 1; i++) {
                if (i == pos) {
                    found = true;
                    break;
                }

                aux = aux.next;
            }

            aux.previous.next = aux.next;
            aux.next.previous = aux.previous;
        }

        if (found) {
            count--;
        }
    }

    public Character get(int pos) {
        Node aux = first;

        if (pos > count-1 || pos < 0) {
            throw new IndexOutOfBoundsException("Index " + pos + " out of bounds for length " + count);
        }

        for (int i = 0; i < count; i++) {
            if (i == pos) {
                return aux.data;
            } else {
                aux = aux.next;
            }
        }

        return 0;
    }

    public int indexOf(Character obj) {
        Node aux = first;

        for (int i = 0; i < count; i++) {
            if (aux.data == obj) {
                return i;
            } else {
                aux = aux.next;
            }
        }

        return -1;
    }

    public void show() {
        Node aux = first;
        String concatener = "";

        for (int i = 0; i < count; i++) {
            if (i == count - 1) {
                concatener += aux.data;
            } else {
                concatener += aux.data + ", ";
            }
        }

        System.out.println("[" + concatener + "]");
    }
}
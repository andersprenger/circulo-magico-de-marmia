package trabalho;

/**
 * MyLinkedList.java
 * Implementação propria de lista encadeada genérica.
 *
 * @param <T> tipo dos elementos da lista.
 * @author Anderson Sprenger
 * @version 23 MAI 21
 */
public class MyLinkedList<T> {
    public class Node {
        T element;
        Node next = null;

        public Node(T element) {
            this.element = element;
        }
    }

    private Node head;
    private Node tail;
    private int count;

    public MyLinkedList() {
        head = null;
        tail = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    public void add(T element) {
        Node e = new Node(element);
        if (head == null) {
            head = e;
        } else {
            tail.next = e;
        }
        tail = e;
        count++;
    }

    public void add(int index, T element) {
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException(index > size() ? "index > size()" : "index < 0");
        } else if (index == size()) {
            add(element);
        } else {
            Node e = new Node(element);
            if (index == 0) {
                e.next = head;
                head = e;
            } else {
                Node aux = head.next;
                Node behindAux = head;
                for (int i = 1; i < index; i++) {
                    aux = aux.next;
                    behindAux = behindAux.next;
                }
                e.next = aux;
                behindAux.next = e;
                count++;
            }
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException(index < 0 ? "index < 0" : "index >= size()");
        } else if (index == size() - 1) {
            return tail.element;
        }
        Node aux = head;
        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }
        return aux.element;
    }

    public T set(int index, T element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException(index < 0 ? "index < 0" : "index >= size()");
        } else if (index == size() - 1) {
            T elementRemoved = tail.element;
            tail.element = element;
            return elementRemoved;
        } else {
            Node aux = head;
            for (int i = 0; i < index; i++) {
                aux = aux.next;
            }
            T elementRemoved = aux.element;
            aux.element = element;
            return elementRemoved;
        }
    }

    public T removeByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException(index < 0 ? "index < 0" : "index >= count");
        } else if (index == 0) {
            T elementRemoved = head.element;
            head = head.next;
            count--;
            return elementRemoved;
        } else {
            Node aux = head;
            for (int i = 1; i < index; i++) {
                aux = aux.next;
            }
            T elementRemoved = aux.next.element;
            aux.next = aux.next.next;
            count--;
            return elementRemoved;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean contains(T element) {
        Node aux = head;
        while (aux != null) {
            if (aux.element.equals(element)) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }

    public int indexOf(T element) {
        int index = 0;
        Node aux = head;
        while (aux != null) {
            if (aux.element.equals(element)) {
                return index;
            }
            aux = aux.next;
            index++;
        }
        return -1;
    }

    public boolean remove(T element) {
        if (head.element.equals(element)) {
            head = head.next;
            count--;
            return true;
        }
        Node behindAux = head;
        Node aux = head.next;
        while (aux != null) {
            if (aux.element.equals(element)) {
                behindAux.next = aux.next;
                count--;
                return true;
            }
            behindAux = behindAux.next;
            aux = aux.next;
        }
        return false;
    }

    public T[] toArray() {
        Object[] array = new Object[size() - 1];
        Node aux = head;
        for (int i = 0; i < size(); i++) {
            array[i] = aux.element;
            aux = aux.next;
        }
        return (T[]) array;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node aux = head;
        str.append("[");
        for (int i = 0; i < size(); i++) {
            str.append(aux.element);
            str.append(", ");
            aux = aux.next;
        }
        str.deleteCharAt(str.length() - 1);
        str.deleteCharAt(str.length() - 1);
        str.append("]");
        return str.toString();
    }

    public String toStringHighlighted(T element) {
        StringBuilder str = new StringBuilder();
        Node aux = head;
        for (int i = 0; i < size(); i++) {
            if (aux.element.equals(element)) {
                str.append("[");
                str.append(aux.element);
                str.append("] ");
            } else {
                str.append(aux.element);
                str.append(" ");
            }
            aux = aux.next;
        }
        return str.toString();
    }
}
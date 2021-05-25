package trabalho;

/**
 * Implementação propria de lista encadeada genérica.
 * @param <T> tipo dos elementos armazenados na lista.
 * @author Anderson Sprenger
 * @version 25 MAI 21
 */
public class MyLinkedList<T> {

    /**
     * Nó usado para estruturação da lista.
     * Contem um elemento da lista e a referencia do nó que contem o próximo elemento da lista.
     * */
    private class Node {
        public T element;
        public Node next;

        public Node(T element) {
            this.element = element;
            next = null;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    /** Referencia para o inicio da lista. */
    private Node head;
    /** Referencia para o fim da lista. */
    private Node tail;

    /** Contador de nós na lista. */
    private int count;

    /** Construtor da lista. */
    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    /**
     * Tamanho da lista
     * @return numero de itens armazenados na lista.
     * Complexidade: O(n) = 1
     */
    public int size() {
        return count;
    }

    /**
     * Adiciona um elemento na lista.
     * @param element elemento a ser adicionado na lista.
     * @throws IllegalArgumentException quando (element == null)
     * Complexidade: O(n) = 1
     */
    public void add(T element) {
        if (element == null) {
            throw new IllegalArgumentException("element == null");
        }

        Node n = new Node(element);

        if (this.head == null) {
            head = n;
        } else {
            tail.next = n;
        }

        tail = n;
        count++;
    }

    /**
     * Adiciona um elemento na lista.
     * @param index posição onde o elemento sera adicionado na lista.
     * @param element a ser adicionado na lista
     * @throws IndexOutOfBoundsException quando (index < 0 || index > size())
     * @throws IllegalArgumentException quando (element == null)
     */
//    public void add(int index, T element) {
//        if (index < 0 || index > size()) {
//            throw new IndexOutOfBoundsException(index < 0 ? "index < 0" : "index > size()");
//        } else if (element == null) {
//            throw new IllegalArgumentException("element == null");
//        } else if (index == size()) {
//            this.add(element);
//        } else {
//
//        }
//    }

    @Override
    public String toString() {
        Node aux = head;

        StringBuilder bodyBuilder = new StringBuilder();

        bodyBuilder.append("[");

        while (aux != null) {
            bodyBuilder.append(aux);
            bodyBuilder.append(", ");
            aux = aux.next;
        }

        bodyBuilder.delete(bodyBuilder.length() - 2, bodyBuilder.length());
        bodyBuilder.append("]");

        return bodyBuilder.toString();
    }
}

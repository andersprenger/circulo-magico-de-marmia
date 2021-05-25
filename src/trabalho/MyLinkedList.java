package trabalho;

/**
 * Implementação propria de lista encadeada genérica.
 *
 * @param <T> tipo dos elementos armazenados na lista.
 * @author Anderson Sprenger
 * @version 25 MAI 21
 */
public class MyLinkedList<T> {

    /**
     * Nó usado para estruturação da lista.
     * Contem um elemento da lista e a referencia do nó que contem o próximo elemento da lista.
     */
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

    /**  Referencia para o inicio da lista. */
    private Node head;
    /** Referencia para o fim da lista. */
    private Node tail;

    /** Contador de nós na lista. */
    private int count;

    /**
     * Construtor da lista.
     */
    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    /**
     * Tamanho da lista
     *
     * Complexidade: O(n) = 1
     * @return numero de itens armazenados na lista.
     */
    public int size() {
        return count;
    }

    /**
     * Adiciona um elemento na lista.
     *
     * Complexidade: O(n) = 1
     * @param element elemento a ser adicionado na lista.
     * @throws IllegalArgumentException quando a posição for invalida
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
     *
     * Complexidade: O(n) = n
     * @param index   posição onde o elemento sera adicionado na lista.
     * @param element a ser adicionado na lista
     * @throws IndexOutOfBoundsException quando quando a posição for invalida
     * @throws IllegalArgumentException  quando o elemento for null
     */
    public void add(int index, T element) {
        if (index < 0 || index > size()) { // se a posição for invalida
            throw new IndexOutOfBoundsException(index < 0 ? "index < 0" : "index > size()");
        } else if (element == null) { // se o elemento for null
            throw new IllegalArgumentException("element == null");
        } else if (index == size()) { // se a posição for o fim da lista
            this.add(element);
        } else {
            Node n = new Node(element);

            if (index == 0) { // se a posição for o inicio da lista
                n.next = head;
                head = n;
            } else { // se a posição for no meio lista
                Node aux = head;
                for (int i = 0; i < index - 1; i++) {
                    aux = aux.next;
                }

                n.next = aux.next;
                aux.next = n;
            }

            count++;
        }
    }

    /**
     * Pega o elemento na posição desejada.
     *
     * Complexidade: O(n) = n
     * @param index posição onde esta o elemento
     * @return elemento na posição
     * @throws IndexOutOfBoundsException quando a posição for invalida
     */
    public T get(int index) {
        if (index < 0 || index > size() - 1) {
            throw new IndexOutOfBoundsException(index < 0 ? "index < 0" : "index > last index");
        } else if (index == size() - 1) {
            return tail.element;
        } else {
            Node aux = head;

            for (int i = 0; i < index; i++) {
                aux = aux.next;
            }

            return aux.element;
        }
    }

    /**
     * Procura a posição de um elemento na lista.
     *
     * Complexidade: O(n) = n
     * @param element elemento a ser buscado.
     * @return posição do elemento na lista.
     */
    public int indexOf(T element) {
        Node aux = head;
        int index = 0;

        while (aux != null) {
            if (aux.element.equals(element)) {
                return index;
            }

            aux = aux.next;
            index++;
        }

        return -1;
    }

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


    public String toStringHighlighted(T element) {
        Node aux = head;

        StringBuilder bodyBuilder = new StringBuilder();

        while (aux != null) {
            if (aux.element.equals(element)) {
                bodyBuilder.append("[");
                bodyBuilder.append(aux);
                bodyBuilder.append("] ");
            } else {
                bodyBuilder.append(aux);
                bodyBuilder.append(" ");
            }
            aux = aux.next;
        }

        return bodyBuilder.toString();
    }
}

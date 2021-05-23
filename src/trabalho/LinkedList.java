package trabalho;

/**
 * LinkedList.java
 * Implementação propria de lista encadeada genérica baseado em código de autoria da prof. Isabel Manssour.
 * @author Anderson Sprenger, Isabel Manssour
 * @param <T> tipo dos elementos da lista.
 */

public class LinkedList<T> {

    // Classe aninhada Node
    private class Node {
        public T element;
        public Node next;

        public Node(T element) {
            this.element = element;
            next = null;
        }
        
        public Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }        
    }

    
    // Referência para o primeiro elemento da lista encadeada.
    private Node head;
    // Referência para o último elemento da lista encadeada.
    private Node tail;
    // Contador para a quantidade de elementos que a lista contem.
    private int count;

    
    /**
     * Construtor da lista.
     */
    public LinkedList() {
        head = null;
        tail = null;
        count = 0;
    }
    
    /**
     * Retorna true se a lista nao contem elementos.
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (count==0); //(head == null);
    }

    /**
     * Retorna o numero de elementos da lista.
     * @return o numero de elementos da lista
     */
    public int size() {
        return count;
    }

    /**
     * Esvazia a lista
     */
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }    

    /**
     * Adiciona um elemento ao final da lista.
     * @param element elemento a ser adicionado ao final da lista
     */
    public void add(T element)  { // O(1)
        Node n = new Node(element);
        if (head == null) {
            head = n;
        } else {
            tail.next = n;
        }
        tail = n;
        count++;
    }
    
    /**
     * Retorna o elemento de uma determinada posição da lista.
     * @param index a posição da lista
     * @return o elemento da posição especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public T get(int index) { // O(n)
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException(index < 0 ? "index < 0" : "index >= size()"); //erro
        }
        
        // Verifica se o elemento buscado esta
        // na ultima posição
        if (index == count-1)
            return tail.element; // retorna o elemento da ultima posição
        Node aux = head; // referencia aux para percorrer a lista
        int c = 0;
        while (c < index) {
            aux = aux.next;
            c++;
        }
        return (aux.element);
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        Node aux = head;

        while (aux != null) {
            s.append(aux.element.toString());
            s.append("\n");
            aux = aux.next;
        }

        return s.toString();
    }    

    /**
     * Insere um elemento em uma determinada posição da lista.
     * @param index a posição da lista onde o elemento sera inserido
     * @param element elemento a ser inserido
     * @throws IndexOutOfBoundsException se (index < 0 || index > size())
     */
    public void add(int index, T element) {
        // Primeiro verifica se index eh valido
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException(index < 0 ? "index < 0" : "index > size()"); // erro
        
        // Criar o node
        Node n = new Node(element);
        // Incrementar count
        count++;
        
        // "Encadear" o nodo criado na lista
        if (index == 0) { // Inserção no inicio
            if (head==null) { // se a lista estiver vazia
                tail = n;
            }
            else { // se a lista não estiver vazia
                n.next = head;
            }
            head = n;
        }
        else if (index == count) { // Inserção no final
            tail.next = n; // "conecta" o nodo na lista
            tail = n; // atualiza tail
        }
        else { // Inserção no meio
            // caminha ate a posição anterior
            Node ant = head;
            for (int i=0; i<index-1; i++)
                ant = ant.next;
            n.next = ant.next; // "conecta" o nodo na lista
            ant.next = n; 
        }
        
    }

    /**
     * Substitui o elemento armazenado em uma determinada posição da lista pelo
     * elemento indicado.
     * @param index a posição da lista
     * @param element o elemento a ser armazenado na lista
     * @return o elemento armazenado anteriormente na posição da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public T set(int index, T element) {
        // Primeiro verifica se index eh valido
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException(index < 0 ? "index < 0" : "index >= size()");
        
        // Verifica se eh a ultima posição
        if (index == count - 1) {
            T rmElement = tail.element;
            tail.element = element;
            return rmElement;
        }
                
        // "caminha" ate a posição index
        Node aux = head;
        for(int i=0; i < index; i++) {
            aux = aux.next;
        }        
        // guarda o elemento que esta na posição index
         T rmElement = aux.element;
         
        // coloca "element" na posição index
         aux.element = element;
         
        // retorna o elemento guardado   
        return rmElement;
    }

    /**
     * Remove a primeira ocorrência do elemento na lista, se estiver presente.
     * @param element o elemento a ser removido
     * @return true se a lista contem o elemento especificado e ele eh removido
     */
    public boolean remove(T element) {
        // retorna false se a lista estiver vazia
        if (count == 0)
            return false;
        
        if(head.element.equals(element)) {
            head = head.next;
            if (count==1) // se tinha apenas um elemento na lista
                tail = null;
            count--;
            return true;
        }
        Node aux = head.next;
        Node ant = head;
        
        while (aux != null) {
            if (aux.element.equals(element)) {
                if (aux == tail) { // remoção do ultimo
                    tail = ant;
                    tail.next = null;
                }
                else { // remoção do meio
                    ant.next = aux.next;
                }                
                count--;
                return true;
            }
            ant = ant.next;
            aux = aux.next;
        }
        
        return false;
    }

    /**
     * Remove o elemento de uma determinada posição da lista.
     * @param index a posição da lista
     * @return o elemento que foi removido da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public T removeByIndex(int index) {
        // Primeiro verifica se o índice eh valido
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException(index < 0 ? "index < 0" : "index >= size()"); // erro
        
        // Remoção do primeiro elemento da lista
        if(index == 0) {
            T elem = head.element; // guarda o elemento que sera removido
            head = head.next;
            if (count==1) // se tinha apenas um elemento na lista
                tail = null;
            count--;
            return elem; // retorna o elemento removido
        }
        
        // Remove do meio ou do final
        
        return null;
    }

    /**
     * Retorna o índice da primeira ocorrência do elemento na lista, ou -1 se a
     * lista nao contem o elemento.
     * @param element o elemento a ser buscado
     * @return o índice da primeira ocorrência do elemento na lista, ou -1 se a
     * lista nao contem o elemento
     */
    public int indexOf(T element) {
        Node aux = head;
        for (int i = 0; i < count; i++) {
            if (aux.element == element) {
                return i;
            } else {
                aux = aux.next;
            }
        }
        return -1;
    }

    /**
     * Retorna true se a lista contem o elemento especificado.
     * @param element o elemento a ser testado
     * @return true se a lista contem o elemento especificado
     */
    public boolean contains(T element) {
        Node aux = head;
        for (int i = 0; i < count; i++) {
            if (aux.element == element) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }
}

package trabalho;

/**
 * MagicCircle.java
 * Trabalho 1, Pt. 2 da disciplina de Algoritmos e Estruturas de Dados I
 * @author Anderson Sprenger
 */
public class MagicCircle {
    // lista com os números dos habitantes, representando o circulo mágico na memória.
    MyLinkedList<Integer> magicCircle;
    // elemento ativo do circulo
    Integer activeElement;

    /**
     * Feita a lista encadeada representando o circulo mágico e adicionado o Grande Rei, como numero 0
     */
    public MagicCircle() {
        this.magicCircle = new MyLinkedList<>();
        magicCircle.add(0);
        activeElement = 0;
    }

    /**
     * Adiciona um elemento no circulo mágico.
     * Complexidade: O(n) = n
     * @param element elemento adicionado no circulo mágico.
     */
    private void join(Integer element) {
        magicCircle.add(joiningElementIndex(), element);
        activeElement = element;
    }

    /**
     * @return a soma entre os vizinhos do elemento ativo no circulo.
     * Complexidade: O(n) = n
     */
    private int neighborsSum() {
        int activeElementIndex = magicCircle.indexOf(activeElement);

        if (activeElementIndex == -1) { // elemento ativo não encontrado
            throw new RuntimeException("O elemento ativo " + activeElement + " não foi encontrado!\n" + magicCircle);

        } else if (activeElementIndex == 0) { // elemento ativo é o primeiro elemento na lista

            if (magicCircle.size() == 1) { // a lista só possui um elemento, ou seja: só tem o Grande rei...
                return 0;

            } else { // elemento ativo é o primeiro da lista (mas não o ultimo)
                int leftNeighbor = magicCircle.get(magicCircle.size() - 1);
                int rightNeighbour = magicCircle.get(activeElementIndex + 1);
                return leftNeighbor + rightNeighbour;
            }

        } else if (activeElementIndex == magicCircle.size() - 1) { // elemento ativo é o ultimo da lista (mas não o primeiro)
            int leftNeighbor = magicCircle.get(activeElementIndex - 1);
            int rightNeighbour = magicCircle.get(0);
            return leftNeighbor + rightNeighbour;

        } else { // elemento ativo não é, nem o primeiro, nem o ultimo elemento da lista
            int leftNeighbor = magicCircle.get(activeElementIndex - 1);
            int rightNeighbour = magicCircle.get(activeElementIndex + 1);
            return leftNeighbor + rightNeighbour;
        }
    }

    /**
     * @return a posição onde ficara o elemento entrando no circulo mágico
     * Complexidade: O(n) = n
     */
    private int joiningElementIndex() {
        // posição do elemento ativo na lista
        int activeElementIndex = magicCircle.indexOf(activeElement);
        // soma da posição do elemento ativo e o resultado da soma dos valores dos vizinhos do elemento ativo
        int joiningElementIndex = activeElementIndex + 1 + neighborsSum();

        if (joiningElementIndex > magicCircle.size()) {
            // se a posição do elemento entrando na lista passar a ultima posição do circulo mágico + 1
            return joiningElementIndex % magicCircle.size();
        } else {
            return joiningElementIndex;
        }
    }

    /**
     * Começa a cerimonia do circulo mágico
     * @param populationSize tamanho da população do Reino de Mármia
     * Complexidade: O(n) = n^2
     */
    public void startCeremony(int populationSize) {
        System.out.println(magicCircle.toStringHighlighted(activeElement));

        // visto que o rei já esta no circulo mágico, e que ele faz parte da população
        // o for começa adicionando o elemento 1, até populationSize, rodando populationSize - 1 vezes.
        for (int i = 1; i <= populationSize; i++) {
            join(i);
            if (i <= 20) {
                System.out.println(magicCircle.toStringHighlighted(activeElement));
            }
        }
    }
}

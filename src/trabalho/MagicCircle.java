package trabalho;

/**
 * MagicCircle.java
 * <p>
 * Trabalho 1, Pt. 2 da disciplina de Algoritmos e Estruturas de Dados I
 *
 * @author Anderson Sprenger
 */
public class MagicCircle {
    /** Lista com os números dos habitantes, representando o circulo mágico na memória. */
    MyLinkedList<Integer> magicCircle;
    /** Elemento ativo do circulo. */
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
     * <p>
     * Complexidade: O(n) = n
     *
     * @param element elemento adicionado no circulo mágico.
     */
    private void join(Integer element) {
        magicCircle.add(joiningElementIndex(), element);
        activeElement = element;
    }

    /**
     * Computa a soma entre os vizinhos do elemento ativo no circulo.
     * <p>
     * Complexidade: O(n) = n
     *
     * @return a soma entre os vizinhos do elemento ativo no circulo.
     * @throws RuntimeException se o elemento ativo do circulo não for encontrado.
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
     * Computa a posição onde ficara o elemento entrando no circulo mágico
     * <p>
     * Complexidade: O(n) = n
     *
     * @return a posição onde ficara o elemento entrando no circulo mágico
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
     * Encontra os vizinhos do Grande Rei no circulo mágico.
     * <p>
     * Complexidade: O(n) = n
     *
     * @return os vizinhos do Grande Rei no circulo.
     * @throws RuntimeException se o Grande Rei não for encontrado.
     */
    private int [] greatKingNeighbors() {
        int kingIndex = magicCircle.indexOf(0);

        if (kingIndex == -1) { // Grande Rei não encontrado
            throw new RuntimeException("O Grande Rei (elemento 0) não foi encontrado!\n" + magicCircle);

        } else if (kingIndex == 0) { // Grande Rei é o primeiro elemento na lista (comportamento esperado)

            if (magicCircle.size() == 1) { // a lista só possui um elemento, ou seja: só tem o Grande rei...
                return new int[]{0, 0};

            } else { // Grande Rei é o primeiro da lista, mas não o ultimo
                int leftNeighbor = magicCircle.size() - 1;
                int rightNeighbour = kingIndex + 1;
                return new int[]{magicCircle.get(leftNeighbor), magicCircle.get(rightNeighbour)};
            }

        } else if (kingIndex == magicCircle.size() - 1) { // Grande Rei é o ultimo da lista (mas não o primeiro)
            int leftNeighbor = magicCircle.get(kingIndex - 1);
            int rightNeighbour = magicCircle.get(0);
            return new int[]{magicCircle.get(leftNeighbor), magicCircle.get(rightNeighbour)};

        } else { // Grande Rei não é, nem o primeiro, nem o ultimo elemento da lista
            int leftNeighbor = magicCircle.get(kingIndex - 1);
            int rightNeighbour = magicCircle.get(kingIndex + 1);
            return new int[]{magicCircle.get(leftNeighbor), magicCircle.get(rightNeighbour)};
        }
    }

    /**
     * Começa a cerimonia do circulo mágico
     * <p>
     * Complexidade: O(n) = n^2
     *
     * @param populationSize tamanho da população do Reino de Mármia
     * @throws RuntimeException se a população informada no parâmetro for menor que 0.
     */
    public void startCeremony(int populationSize) {
        if (populationSize <= 0) {
            throw new RuntimeException("populationSize <= 0 - populationSize: " + populationSize);
        }

        System.out.println(magicCircle.toStringHighlighted(activeElement));

        // visto que o rei já esta no circulo mágico, e que ele faz parte da população
        // o for começa adicionando o elemento 1, até populationSize, rodando populationSize - 1 vezes.
        for (int i = 1; i <= populationSize; i++) { // n
            join(i); // n
            if (i <= 20) {
                System.out.println(magicCircle.toStringHighlighted(activeElement));
            }
        }

        int [] greatKingNeighbors = greatKingNeighbors();
        System.out.println("Vizinhos do Grande Rei: " + greatKingNeighbors[0] + " e " + greatKingNeighbors[1] + ".");
    }
}

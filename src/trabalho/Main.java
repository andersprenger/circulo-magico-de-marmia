package trabalho;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MagicCircle magicCircle = new MagicCircle();
        Scanner scn = new Scanner(System.in);

        System.out.println("Circulo Mágico de Mármia - com 20 habitantes:");
        magicCircle.startCeremony(20); // Complexidade: O(n) = n^2
        System.out.println();

        while (true) {
            System.out.println("Digite o tamanho da população, ou 0 para sair:");
            int populationSize = scn.nextInt();
            if (populationSize <= 0)
                return;
            magicCircle = new MagicCircle();
            magicCircle.startCeremony(populationSize);
        }
    }
}

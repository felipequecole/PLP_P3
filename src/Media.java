import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by felipequecole on 07/06/17.
 * @author felipequecole
 */
public class Media {
    public static void main (String[] args){
        int opt = 0;
        do {
            Scanner in = new Scanner(System.in);
            System.out.println("Que operação deseja fazer?");
            System.out.println("1. Calcular media");
            System.out.println("2. Que nota preciso?");
            System.out.println("0. Sair");
            opt = in.nextInt();
            if (opt == 1) {
                int tamanho;
                System.out.println("Insira o número de provas: ");
                tamanho = in.nextInt();
                double[] notas = Media.createVetor(tamanho);
                double media = Media.calculaMedia(notas, tamanho);
                if (media < 6d) {
                    System.out.println("Reprovado. Media final: " + media);
                } else {
                    System.out.println("Aprovado. Media final: " + media);
                }
            } else if (opt == 2) {
                System.out.println("Digite a quantidade total de provas: ");
                int qtdTotal = in.nextInt();
                System.out.println("Digite a quantidade de provas já aplicadas: ");
                int qtdFeitas = in.nextInt();
                double[] feitas = Media.createVetor(qtdFeitas);
                double[] precisa = Media.quantoPreciso(feitas, qtdFeitas, qtdTotal);
                int faltam = qtdTotal - qtdFeitas;
                System.out.println("Você precisa de: ");
                for (int i = 0; i < faltam; i++) {
                    System.out.println(precisa[i] + " na P" + (qtdFeitas + i + 1));
                }
                System.out.println("Boa sorte! :)");
            }
        } while (opt != 0);
    }

    private static double[] createVetor(int tamanho){
        double[] array = new double[tamanho];
        Scanner in = new Scanner(System.in);
        for(int i = 0; i < tamanho; i++) {
            System.out.println("Insira a " + (i+1) + "a nota:");
            array[i] = in.nextDouble();
        }
        return array;
    }

    private static double calculaMedia(double[] notas, int tamanho){
        double total = 0d;
        for (int i = 0; i < tamanho; i++){
            total += notas[i];
        }
        return total / tamanho;
    }

    private static double[] quantoPreciso(double[] provasFeitas, int qtdFeitas, int qtdTotal){
        int faltam = qtdTotal - qtdFeitas;
        double[] notas = new double[faltam];
        double[] notasTotais = new double[qtdTotal];
        for(int i = 0; i < qtdTotal;  i++){
            if(i < qtdFeitas){
                notasTotais[i] = provasFeitas[i];
            } else {
                notasTotais[i] = 0d;
            }
        }
        double mediaAtual = Media.calculaMedia(notasTotais, qtdTotal);
        double sumAlvo = qtdTotal * 6d;
        double sumAtual = mediaAtual * qtdTotal;
        sumAlvo = sumAlvo - sumAtual;
        sumAlvo = sumAlvo / faltam;
        for(int i = 0; i < faltam; i++){
            notas[i] = sumAlvo;
        }
        return notas;
    }
}

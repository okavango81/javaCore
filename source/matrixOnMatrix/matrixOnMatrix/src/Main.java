import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.print("Digite o número de linhas da matriz: ");
        int lin = scanner.nextInt();
        int pos = 0;

        System.out.print("Digite o número de colunas da matriz: ");
        int col = scanner.nextInt();

        int[][] matriz = new int[lin][col];

        IntStream.range(0, lin).forEach(i ->
        {
            System.out.printf("%nDigite %d número(s) separado(s) por espaço e depois tecle \"Enter\" %n", col);
            for (int j = 0; j < col; j++)
            {
                matriz[i][j] = scanner.nextInt();
            }
        });

        System.out.printf("%nMatriz completa:%n");
        for (int i = 0; i < lin; i++)
        {
            for (int j = 0; j < col; j++)
            {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.print("Busque por um número na matriz: ");
        int num = scanner.nextInt();

        for (int i = 0; i < lin; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (num == matriz[i][j])
                {
                    pos++;
                    System.out.printf("%n%dº Posição na matriz: Linha %d, Coluna %d%n", pos, (i + 1), (j + 1));

                    if ((j - 1) >= 0)
                    {
                        System.out.printf("Esquerda %d: %n", matriz[i][(j - 1)]);
                    }
                    if ((j + 1) <= (col - 1))
                    {
                        System.out.printf("Direita %d: %n", matriz[i][(j + 1)]);
                    }
                    if ((i - 1) >= 0)
                    {
                        System.out.printf("Acima %d: %n", matriz[i - 1][j]);
                    }
                    if ((i + 1) <= (lin - 1))
                    {
                        System.out.printf("Abaixo %d: %n", matriz[i + 1][j]);
                    }
                }
            }
        }


        scanner.close();
    }
}

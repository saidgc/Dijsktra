import java.util.*;
public class dij {
    private int D[] = new int[10];
    private int Tp[][] = new int[10][10];
    private static int P[][] = new int[100][100];
    private static char[] abc = new char[100];


    private void dijkstra(int n, int s) {

        boolean visto[] = new boolean[10];
        int w, c, min, vertice = 0;

        for (w = 1; w <= n; w++) {
            visto[w] = false;
            if (this.Tp[s][w] == -1) {
                this.D[w] = 9999;
            } else {
                this.D[w] = this.Tp[s][w];
            }
        }

        this.D[s] = 0;
        visto[s] = true;
        c = 2;

        while (c <= n) {
            min = 999;
            for (s = 1; s <= n; s++) {
                if (this.D[s] < min && !visto[s]) {
                    min = this.D[s];
                    vertice = s;
                }
            }
            visto[vertice] = true;
            c++;
            for (w = 1; w <= n; w++) {
                if (this.D[w] > this.D[vertice] + this.Tp[vertice][w] && !visto[w]) {
                    this.D[w] = this.D[vertice] + this.Tp[vertice][w];
                }
            }
        }
    }

    public static void main(String args[]) {
        //llenar un vector con el abecedario
        for (int i = 65; i <= 100; i++) {
            abc[i - 64] = (char) i;
        }
        int nodos, i, j;
        int A[][];

        Scanner in = new Scanner(System.in);
        System.out.println("Numero de nodos\n");
        nodos = in.nextInt();
        dij d = new dij();
        System.out.println("Introduce la matriz de transciciones pesada (por reglones)\n");
        for (i = 1; i <= nodos; i++)
            for (j = 1; j <= nodos; j++) {
                d.Tp[i][j] = in.nextInt();
                if (d.Tp[i][j] == -1)
                    d.Tp[i][j] = 999;
            }

        //Encontrar el camino mas corto con la matriz Tp
        A = d.Tp;
        for (int k = 1; k <= nodos; k++) {
            for (i = 1; i <= nodos; i++) {
                for (j = 1; j <= nodos; j++) {
                    if (A[i][k] + A[k][j] < A[i][j]) {
                        A[i][j] = A[i][k] + A[k][j];
                        P[i][j] = k;
                    }
                }
            }
        }

        //cicla dijktra
        for (int k = 1; k <= nodos; k++) {
            d.dijkstra(nodos, k);
            System.out.println();

            for (i = 1; i <= nodos; i++) {
                if (d.D[i] != 999 && d.D[i] != 0) {
                    System.out.print(abc[k] + " -> ");
                    camino(k, i);
                    System.out.println(abc[i] + " costo minimo: " + d.D[i]);
                } else {
                    System.out.println(abc[k] + " -> " + abc[i] + " No existe camino");
                }
                A[k][i] = d.D[i];
            }
        }


    }

    static private void camino(int i, int j) {
        int K;
        K = P[i][j];
        if (K == 0)
            return;
        camino(i, K);
        System.out.print(abc[K] + " -> ");
        camino(K, j);
    }

}

import java.util.*;
public class dij {
    private int distancia[] = new int[10];
    private int costo[][] = new int[10][10];

    private void calc(int n, int s) {
        boolean visto[] = new boolean[10];
        int w, c, min, vertice = 0;

        for (w = 1; w <= n; w++) {
            visto[w] = false;
            if (this.costo[s][w] == -1) {
                this.distancia[w] = 9999;
            } else {
                this.distancia[w] = this.costo[s][w];
            }
        }

        this.distancia[s] = 0;
        visto[s] = true;
        c = 2;
        while (c <= n) {
            min = 999;
            for (s = 1; s <= n; s++) {
                if (this.distancia[s] < min && !visto[s]) {
                    min = this.distancia[s];
                    vertice = s;
                }
            }
            visto[vertice] = true;
            c++;
            for (w = 1; w <= n; w++) {
                if (this.distancia[w] > this.distancia[vertice] + this.costo[vertice][w] && !visto[w]) {
                    this.distancia[w] = this.distancia[vertice] + this.costo[vertice][w];
                }
            }
        }

    }

    public static void main(String args[]) {
        int nodes, source, i, j;
        Scanner in = new Scanner(System.in);
        System.out.println("Numero de nodos\n");
        nodes = in.nextInt();
        dij d = new dij();
        System.out.println("Introduce la matriz de transciciones pesada (por reglones)\n");
        for (i = 1; i <= nodes; i++)
            for (j = 1; j <= nodes; j++) {
                d.costo[i][j] = in.nextInt();
                if (d.costo[i][j] == 0)
                    d.costo[i][j] = 999;
            }
        System.out.println("Introduce la raiz:\n");
        source = in.nextInt();

        d.calc(nodes, source);
        System.out.println("EL camino mas corto desde \t" + source + "\t Hasta los otros es: \n");
        for (i = 1; i <= nodes; i++)
            if (i != source)
                System.out.println("Raiz :" + source + "\t destino :" + i + "\t Costo minimo :" + d.distancia[i] + "\t");
    }
}

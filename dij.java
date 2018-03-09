import java.util.*;
public class dij {
    private int distancia[] = new int[10];
    private int costo[][] = new int[10][10];

    private void calc(int n, int s) {
        int vandera[] = new int[n + 1];
        int i, minpos = 1, k, c, minimum;

        for (i = 1; i <= n; i++) {
            vandera[i] = 0;
            this.distancia[i] = this.costo[s][i];
        }
        System.out.println();
        c = 2;
        while (c <= n) {
            minimum = 99;
            for (k = 1; k <= n; k++) {
                System.out.print("D[" + k + "]=" + distancia[k] + " ");
                if (this.distancia[k] <= minimum && vandera[k] != 1) {
                    minimum = this.distancia[i];
                    minpos = k;
                }
            }
            System.out.println();
            vandera[minpos] = 1;
            c++;
            for (k = 1; k <= n; k++) {
                System.out.println(" "+this.distancia[minpos] +" + "+ this.costo[minpos][k] +" < "+ this.distancia[k]+ " && "+ vandera[k] +" != "+ 1);
                if (this.distancia[minpos] + this.costo[minpos][k] < this.distancia[k] && vandera[k] != 1) {
                    System.out.println(this.distancia[k] + "=" + this.distancia[minpos] + "+" + this.costo[minpos][k]);
                    this.distancia[k] = this.distancia[minpos] + this.costo[minpos][k];
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

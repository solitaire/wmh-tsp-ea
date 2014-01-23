package graph;

import java.io.InputStream;
import java.util.Scanner;

public class GraphReader
{
        public static double[][] readWeights(InputStream in)
        {
                final Scanner scanner = new Scanner(in);
                final int D = scanner.nextInt();
                final double weights[][] = new double[D][D];
                for (int y = 0; y < D; y++)
                {
                        for (int x = 0; x < D; x++)
                        {
                                weights[x][y] = scanner.nextDouble();
                        }
                }
                scanner.close();
                return weights;
        }
}
package testing;

import graph.Graph;

import java.util.Locale;
import java.util.Random;

import optimization.Optimizer;
import optimization.ea.EA;
import optimization.greedy.Greedy;

public class Main
{
	public final static int SEED = 1;
	public final static Random rand = new Random(SEED);

	private final static double DEFAULT_MUTATION_PROBABILITY = 0.1;
	private final static double DEFAULT_CROSSOVER_PROBABILITY = 0.9;
	private final static int DEFAULT_N_TO_D_RATIO = 10;
	private final static boolean DEFAULT_GREEDY = false;
	private final static boolean DEFAULT_GREEDY_START = false;
	private final static int RUNS = 15;

	public static void main(String[] args)
	{
		// Sets default locale to always have 1.23 not 1,23
		Locale.setDefault(Locale.US);
		final Graph graph = new Graph(System.in);

		double mutationProbability = DEFAULT_MUTATION_PROBABILITY;
		double crossoverProbability = DEFAULT_CROSSOVER_PROBABILITY;
		int nToDRatio = DEFAULT_N_TO_D_RATIO;
		boolean greedy = DEFAULT_GREEDY;
		boolean greedyStart = DEFAULT_GREEDY_START;
		if (args.length >= 1)
		{
			if (args[0].equals("greedy"))
			{
				greedy = true;
			}
			else
			{
				mutationProbability = Double.parseDouble(args[0]);
			}
		}
		if (args.length >= 2)
		{
			crossoverProbability = Double.parseDouble(args[1]);
		}
		if (args.length >= 3)
		{
			nToDRatio = Integer.parseInt(args[2]);
		}
		if (args.length >= 4 && args[3].equals("greedystart"))
		{
			greedyStart = true;
		}
		
		Optimizer optimizer = new EA(graph, mutationProbability, crossoverProbability, nToDRatio, greedyStart);
		if (greedy)
		{
			optimizer = new Greedy(graph);
		}
		
		for (int i = 0; i < RUNS; i++)
		{
			System.out.println(optimizer.optimize());
		}
	}
}

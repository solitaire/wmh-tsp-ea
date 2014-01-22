package testing;

import graph.Graph;
import graph.builder.GraphBuilder;

import java.util.Random;

import optimization.BestResult;
import optimization.Optimizer;
import optimization.ea.EA;
import optimization.greedy.Greedy;

public class Main
{
	public final static int SEED = 1;
	public final static Random rand = new Random(SEED);

	private final static double DEFAULT_MUTATION_PROBABILITY = 0.1;
	private final static double DEFAULT_CROSSOVER_PROBABILITY = 0.9;
	private final static int RUNS = 15;
	private final static String OSM_MAP_NAME = "europe_poland-gh";

	public static void main(String[] args)
	{
		final Graph graph = GraphBuilder.build(System.in, OSM_MAP_NAME);

		double mutationProbability = DEFAULT_MUTATION_PROBABILITY;
		double crossoverProbability = DEFAULT_CROSSOVER_PROBABILITY;
		Optimizer optimizer = new EA(graph, mutationProbability, crossoverProbability);
		if (args.length >= 1)
		{
			if (args[0].equals("greedy"))
			{
				optimizer = new Greedy(graph);
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
			if (args[2].equals("greedystart"))
			{
				optimizer = new EA(graph, mutationProbability, crossoverProbability, true);
			}
		}

		for (int i = 0; i < RUNS; i++)
		{
			BestResult bestResult = optimizer.optimize();
			System.out.println(bestResult.getOptimum());
			System.out.println(bestResult.getSolution());
		}
	}
}

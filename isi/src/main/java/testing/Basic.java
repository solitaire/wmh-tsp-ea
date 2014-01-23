package testing;

import graph.Graph;
import optimization.BestResult;
import optimization.Optimizer;
import optimization.ea.EA;
import optimization.greedy.Greedy;
import settings.AlgorithmSettings;

public class Basic
{

	public static void main(String[] args)
	{
		final Graph graph = new Graph(System.in);

		double mutationProbability = AlgorithmSettings.DEFAULT_MUTATION_PROBABILITY;
		double crossoverProbability = AlgorithmSettings.DEFAULT_CROSSOVER_PROBABILITY;
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

		for (int i = 0; i < AlgorithmSettings.RUNS; i++)
		{
			BestResult bestResult = optimizer.optimize();
			System.out.println(bestResult.getOptimum());
		}
	}
}

package testing;

import graph.Graph;

import java.util.Locale;
import java.util.Random;

import optimization.Evaluator;
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
	private final static int FUN_EVALS_TO_D_RATIO = 100000;

	public static void main(String[] args)
	{
		// Sets default locale to always have 1.23 not 1,23
		Locale.setDefault(Locale.US);
		final Graph graph = new Graph(System.in);
		final int MAX_FUN_EVALS = FUN_EVALS_TO_D_RATIO * graph.D;
		final Evaluator evaluator = new Evaluator(graph, MAX_FUN_EVALS);

		double mutationProbability = DEFAULT_MUTATION_PROBABILITY;
		double crossoverProbability = DEFAULT_CROSSOVER_PROBABILITY;
		Optimizer optimizer = new EA(evaluator, graph.D, mutationProbability, crossoverProbability);
		if (args.length >= 1)
		{
			if (args[0].equals("greedy"))
			{
				optimizer = new Greedy(evaluator, graph);
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

		for (int i = 0; i < RUNS; i++)
		{
			evaluator.reset();
			System.out.println(optimizer.optimize());
		}
	}
}

package testing;

import java.util.Locale;
import java.util.Random;

import ea.Crossover;
import ea.EA;
import ea.Evaluator;
import ea.Solution;
import graph.Graph;

public class Main
{
	public final static int SEED = 1;
	public final static Random rand = new Random(SEED);

	private final static int RUNS = 15;
	private final static int FUN_EVALS_TO_D_RATIO = 100000;

	public static void main(String[] args)
	{
		// Sets default locale to always have 1.23 not 1,23
		Locale.setDefault(Locale.US);
		final Graph graph = new Graph(System.in);
		final int MAX_FUN_EVALS = FUN_EVALS_TO_D_RATIO * graph.D;
		for (int i = 0; i < RUNS; i++)
		{
			final Evaluator evaluator = new Evaluator(graph, MAX_FUN_EVALS);
			final EA ea = new EA(evaluator, graph.D);
			// TODO: use System.out.println(ea.optimize());
			System.out.println(6 + rand.nextDouble() * 10);
		}
		testCrossover();
	}

	public static void testCrossover()
	{
		Solution parent1 = new Solution(new int[]
		{ 2, 6, 7, 1, 5, 4, 8, 3 });
		Solution parent2 = new Solution(new int[]
		{ 7, 5, 6, 3, 8, 2, 1, 4 });
		System.err.println(Crossover.crossover(parent1, parent2));
	}
}

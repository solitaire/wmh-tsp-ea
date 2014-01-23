package optimization.ea;

import graph.Graph;
import optimization.BestResult;
import optimization.Evaluator;
import optimization.Optimizer;
import optimization.Solution;
import optimization.greedy.Greedy;
import settings.AlgorithmSettings;

public class EA extends Optimizer
{
	private final int N;
	private final int N_TO_D_RATIO = 10;
	private final int FUN_EVALS_TO_D_RATIO = 100000;
	private final Mutation mutation;
	private final double crossoverProbability;
	private final boolean greedyStart;

	public EA(Graph graph, double mutationProbability, double crossoverProbability)
	{
		this(graph, mutationProbability, crossoverProbability, false);
	}

	public EA(Graph graph, double mutationProbability, double crossoverProbability, boolean greedyStart)
	{
		super(graph);
		N = N_TO_D_RATIO * D;
		this.mutation = new Mutation(mutationProbability);
		this.crossoverProbability = crossoverProbability;
		this.greedyStart = greedyStart;
	}

	public BestResult optimize()
	{
		Population actual;
		if (greedyStart)
		{
			final Greedy greedy = new Greedy(graph);
			actual = new Population(N, greedy);
		}
		else
		{
			actual = new Population(N, D);
		}
		final Population children = new Population(N, D);
		final int MAX_FUN_EVALS = FUN_EVALS_TO_D_RATIO * graph.D;
		final Evaluator evaluator = new Evaluator(graph, MAX_FUN_EVALS);
		do
		{
			for (int i = 0; i < N; i++)
			{
				final Solution a = select(children);
				if (AlgorithmSettings.rand.nextDouble() < crossoverProbability)
				{
					final Solution b = select(children);
					children.solutions[i] = mutation.mutate(Crossover.crossover(a, b));
				}
				else
				{
					children.solutions[i] = mutation.mutate(a);
				}
			}
			succesion(actual, children, evaluator);
		}
		while (!evaluator.hasReachedMaxFunEvals());
		return new BestResult(evaluator.getBestObservedScore(), evaluator.getBestObservedSolution());
	}

	private Solution select(Population pop)
	{
		return pop.solutions[AlgorithmSettings.rand.nextInt(N)];
	}

	private void succesion(Population actual, Population children, Evaluator evaluator)
	{
		for (int i = 0; i < N; i++)
		{
			if (evaluator.isBetter(children.solutions[i], actual.solutions[i]))
			{
				actual.solutions[i] = new Solution(children.solutions[i]);
			}
		}
	}
}

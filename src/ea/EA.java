package ea;

import testing.Main;

public class EA
{
	public final int N_TO_D_RATIO = 10;
	public final int D;
	public final int N;
	private final Evaluator evaluator;
	private final Mutation mutation;
	private final double crossoverProbability;

	public EA(Evaluator evaluator, int d, double mutationProbability, double crossoverProbabilty)
	{
		D = d;
		N = N_TO_D_RATIO * d;
		this.evaluator = evaluator;
		this.mutation = new Mutation(mutationProbability);
		this.crossoverProbability = crossoverProbabilty;
	}

	public double optimize()
	{
		final Population actual = new Population(N, D);
		final Population children = new Population(N, D);
		do
		{
			for (int i = 0; i < N; i++)
			{
				final Solution a = select(children);
				if (Main.rand.nextDouble() < crossoverProbability)
				{
					final Solution b = select(children);
					children.solutions[i] = mutation.mutate(Crossover.crossover(a, b));
				}
				else
				{
					children.solutions[i] = mutation.mutate(a);
				}
			}
			succesion(actual, children);
		}
		while (!evaluator.hasReachedMaxFunEvals());
		return evaluator.getBestObservedScore();
	}

	private Solution select(Population pop)
	{
		return pop.solutions[Main.rand.nextInt(N)];
	}

	private void succesion(Population actual, Population children)
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

package ea;

import java.util.Random;

public class EA
{
	public static final double CROSSOVER_PROBABILITY = 0.9;
	public static final double MUTATION_PROBABILITY = 0.5;
	public static final int N_TO_D_RATIO = 10;
	public final int D;
	public final int N;
	public final Random rand;
	private final Evaluator evaluator;

	public EA(Evaluator evaluator, int d, Random rand)
	{
		D = d;
		N = N_TO_D_RATIO * d;
		this.rand = rand;
		this.evaluator = evaluator;
	}

	public Solution optimize()
	{
		final Population actual = new Population(N, D, rand);
		final Population children = new Population(N, D, rand);
		do
		{
			for (int i = 0; i < N; i++)
			{
				final Solution a = select(children);
				if (rand.nextDouble() < CROSSOVER_PROBABILITY)
				{
					final Solution b = select(children);
					children.solutions[i] = mutate(crossover(a, b));
				}
				else
				{
					children.solutions[i] = mutate(a);
				}
			}
			succesion(actual, children);
		}
		while (!evaluator.hasReachedMaxFunEvals());
		return evaluator.getBestObservedSolution();
	}

	private Solution mutate(Solution solution)
	{
		// TODO
		return null;
	}

	private Solution select(Population pop)
	{
		return pop.solutions[rand.nextInt(N)];
	}
	
	private Solution crossover(Solution parent1, Solution parent2) {
		return Crossover.generateOffSpring(parent1, parent2);
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

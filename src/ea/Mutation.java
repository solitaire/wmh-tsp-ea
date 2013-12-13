package ea;

import testing.Main;

public class Mutation
{
	private final double mutationProbability;

	public Mutation(double mutationProbability)
	{
		this.mutationProbability = mutationProbability;
	}

	public Solution mutate(Solution solution)
	{
		final Solution mutant = new Solution(solution);
		final int d = solution.cities.length;
		final int repeats = (int) Math.ceil(d * mutationProbability);
		for (int r = 0; r < repeats; r++)
		{
			final int i = Main.rand.nextInt(d);
			int j;
			do
			{
				j = Main.rand.nextInt(d);
			}
			while (i != j);

			final int city = mutant.cities[i];
			mutant.cities[i] = solution.cities[j];
			mutant.cities[j] = city;
		}
		return mutant;
	}
}

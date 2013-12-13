package ea;

import testing.Main;

public class Mutation
{
	public static Solution mutate(Solution solution)
	{
		final Solution mutant = new Solution(solution);
		final int d = solution.cities.length;
		final int repeats = (int) Math.ceil(d * EA.MUTATION_PROBABILITY);
		for (int r = 0; r < repeats; r++)
		{
			final int i = Main.rand.nextInt(d);
			int j = i;
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

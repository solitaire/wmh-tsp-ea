package ea;

import testing.Main;

public class Mutation
{
	public static Solution mutate(Solution solution)
	{
		final Solution mutant = new Solution(solution);
		final int D = solution.cities.length;
		for (int i = 0; i < D; i++)
		{
			if (Main.rand.nextDouble() < EA.MUTATION_PROBABILITY)
			{
				final int index1 = Main.rand.nextInt(D);
				int index2 = index1;
				do
				{
					index2 = Main.rand.nextInt(D);
				}
				while (index1 != index2);
				mutant.cities[index1] = solution.cities[index2];
				mutant.cities[index2] = solution.cities[index1];
			}
		}
		return mutant;
	}
}

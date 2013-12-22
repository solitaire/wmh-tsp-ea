package optimization.ea;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import optimization.Solution;
import testing.Main;

public class Crossover
{
	public static Solution crossover(Solution parent1, Solution parent2)
	{
		final Solution child = new Solution(parent1);

		int i = Main.rand.nextInt(parent1.cities.length);
		int j = Main.rand.nextInt(parent2.cities.length);
		if (i > j)
		{
			final int temp = i;
			i = j;
			j = temp;
		}

		final int swathLength = j - i + 1;
		final Set<Integer> swath = new HashSet<Integer>(swathLength);
		for (int k = i; k <= j; k++)
		{
			swath.add(parent1.cities[k]);
		}

		final Set<Integer> available = new LinkedHashSet<Integer>(Arrays.asList(parent2.cities));
		available.removeAll(swath);

		final Iterator<Integer> it = available.iterator();
		for (int k = 0; k < i; k++)
		{
			child.cities[k] = it.next();
		}
		for (int k = j + 1; k < child.cities.length; k++)
		{
			child.cities[k] = it.next();
		}

		return child;
	}
}

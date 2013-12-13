package ea;

import java.util.HashMap;
import java.util.Map;

import testing.Main;

public class Crossover
{
	public static Solution crossover(Solution parent1, Solution parent2)
	{
		final Solution children1 = new Solution(parent1);
		final Solution children2 = new Solution(parent2);
		
		System.err.println(children1);
		System.err.println(children2);

		int i = Main.rand.nextInt(parent1.cities.length);
		int j = Main.rand.nextInt(parent2.cities.length);
		if (i > j)
		{
			final int temp = i;
			i = j;
			j = temp;
		}
		
		System.err.println(i);
		System.err.println(j);

		final int length = j - i + 1;

		final Map<Integer, Integer> children1Map = new HashMap<Integer, Integer>(length);
		final Map<Integer, Integer> children2Map = new HashMap<Integer, Integer>(length);

		for (int k = i; k < i + length; k++)
		{
			final int city1 = parent1.cities[k];
			final int city2 = parent2.cities[k];

			// swap solutions between two crossover points
			children1.cities[k] = city2;
			children2.cities[k] = city1;

			// create mapping
			children1Map.put(city1, city2);
			children2Map.put(city2, city1);
		}
		
		System.err.println(children1);
		System.err.println(children2);
		
		System.err.println(children1Map);
		System.err.println(children2Map);

		repairChildren(i, children1, children2Map);
		repairChildren(i, children2, children1Map);
		
		return children1;
	}

	private static void repairChildren(int start, Solution children, Map<Integer, Integer> map)
	{
		for (int i = 0; i < children.cities.length; i++)
		{
			if (i < start || i >= start + map.size())
			{
				final int city = children.cities[i];
				if (map.containsKey(city))
				{
					children.cities[i] = map.get(city);
				}
			}
		}
	}
}

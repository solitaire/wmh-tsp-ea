package ea;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Crossover
{

	public static Solution generateOffSpring(Solution parent1, Solution parent2)
	{
		Solution offspring1 = new Solution(parent1);
		Solution offspring2 = new Solution(parent2);

		Random rand = new Random();
		int crossoverPoint1 = rand.nextInt(parent1.cities.length);
		int crossoverPoint2 = rand.nextInt(parent2.cities.length);

		int start = Math.min(crossoverPoint1, crossoverPoint1);
		int length = Math.abs(crossoverPoint1 - crossoverPoint2) + 1;

		Map<Integer, Integer> offspring1Mapping = new HashMap<Integer, Integer>();
		Map<Integer, Integer> offspring2Mapping = new HashMap<Integer, Integer>();

		for (int i = 0; i < length; i++)
		{

			int index = i + start;
			int city1 = parent1.cities[index];
			int city2 = parent2.cities[index];

			// swap solutions between two crossover points
			offspring1.cities[index] = city2;
			offspring2.cities[index] = city1;

			// create mapping
			offspring1Mapping.put(city1, city2);
			offspring2Mapping.put(city2, city1);
		}

		normalizeOffspring(start, offspring1, offspring2Mapping);
		normalizeOffspring(start, offspring2, offspring1Mapping);

		// return random offspring
		return Arrays.asList(offspring1, offspring2).get(rand.nextInt(2));
	}

	private static void normalizeOffspring(int start, Solution offspring,
			Map<Integer, Integer> mapping)
	{
		for (int i = 0; i < offspring.cities.length; i++)
		{
			if (i < start || i >= start + mapping.size())
			{
				int city = offspring.cities[i];
				if (mapping.containsKey(city))
				{
					offspring.cities[i] = mapping.get(city);
				}
			}
		}
	}
}

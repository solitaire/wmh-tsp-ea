package ea;

import java.util.Arrays;
import java.util.Random;

public class Solution
{
	public final int cities[];

	/** Create random solution. */
	public Solution(int d, Random rand)
	{
		cities = new int[d];
		// TODO using rand
	}

	/** Deep copy. */
	public Solution(Solution other)
	{
		cities = new int[other.cities.length];
		System.arraycopy(other.cities, 0, cities, 0, cities.length);
	}

	public Solution crossover(Solution other)
	{
		// TODO
		return null;
	}

	@Override
	public String toString()
	{
		return Arrays.toString(cities);
	}
}
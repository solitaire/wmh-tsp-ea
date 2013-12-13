package ea;

import java.util.Arrays;
import java.util.Collections;

import testing.Main;

public class Solution
{
	public final int cities[];

	/** Create random solution. */
	public Solution(int d)
	{
		cities = new int[d];
		for (int i = 0; i < d; d++)
		{
			cities[i] = i + 1;
		}
		Collections.shuffle(Arrays.asList(cities), Main.rand);
	}

	/** Deep copy. */
	public Solution(Solution other)
	{
		cities = new int[other.cities.length];
		System.arraycopy(other.cities, 0, cities, 0, cities.length);
	}

	public Solution(int cities[])
	{
		this.cities = cities;
	}

	@Override
	public String toString()
	{
		return Arrays.toString(cities);
	}
}
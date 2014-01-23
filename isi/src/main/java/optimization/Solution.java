package optimization;

import java.util.Arrays;
import java.util.Collections;

import settings.AlgorithmSettings;

public class Solution
{
	public final Integer cities[];

	/** Create random solution. */
	public Solution(int d)
	{
		cities = new Integer[d];
		for (int i = 0; i < d; i++)
		{
			cities[i] = i;
		}
		Collections.shuffle(Arrays.asList(cities), AlgorithmSettings.rand);
	}

	/** Deep copy. */
	public Solution(Solution other)
	{
		cities = new Integer[other.cities.length];
		System.arraycopy(other.cities, 0, cities, 0, cities.length);
	}

	public Solution(Integer cities[])
	{
		this.cities = cities;
	}

	@Override
	public String toString()
	{
		return Arrays.toString(cities);
	}
}
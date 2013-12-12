package ea;

import java.util.Arrays;
import java.util.Random;

public class Population
{
	public final Solution[] solutions;

	/** Create random population. */
	public Population(int n, int d, Random rand)
	{
		solutions = new Solution[n];
		for (int i = 0; i < n; i++)
		{
			solutions[i] = new Solution(d, rand);
		}
	}

	@Override
	public String toString()
	{
		return Arrays.toString(solutions);
	}

	public int size()
	{
		return solutions.length;
	}
}

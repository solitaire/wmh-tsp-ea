package optimization.ea;

import java.util.Arrays;

import optimization.Solution;
import optimization.greedy.Greedy;

public class Population
{
	public final Solution[] solutions;

	/** Create random population. */
	public Population(int n, int d)
	{
		solutions = new Solution[n];
		for (int i = 0; i < n; i++)
		{
			solutions[i] = new Solution(d);
		}
	}

	/** Create population containing n greedy solutions. */
	public Population(int n, Greedy greedy)
	{
		solutions = new Solution[n];
		for (int i = 0; i < n; i++)
		{
			greedy.optimize();
			solutions[i] = new Solution(greedy.getSolution());
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

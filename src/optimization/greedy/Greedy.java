package optimization.greedy;

import graph.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import optimization.Optimizer;
import optimization.Solution;
import testing.Main;

public class Greedy extends Optimizer
{
	private Solution solution;

	public Greedy(Graph graph)
	{
		super(graph);
	}

	@Override
	public double optimize()
	{
		final List<Integer> visitedCities = new ArrayList<Integer>(D);
		double length = 0.0;
		final int START_CITY = Main.rand.nextInt(D);
		visitedCities.add(START_CITY);
		final Set<Integer> unvisited = new HashSet<Integer>(D);
		for (int i = 0; i < D; i++)
		{
			unvisited.add(i);
		}
		unvisited.remove(START_CITY);
		int actualCity = START_CITY;
		do
		{
			final int nearestCity = getNearestUnvisitedCity(unvisited, actualCity);
			length += graph.weights[actualCity][nearestCity];
			visitedCities.add(nearestCity);
			unvisited.remove(nearestCity);
			actualCity = nearestCity;
		}
		while (!unvisited.isEmpty());
		length += graph.weights[actualCity][START_CITY];
		solution = new Solution(D);
		visitedCities.toArray(solution.cities);
		return length;
	}

	private int getNearestUnvisitedCity(Set<Integer> unvisited, int from)
	{
		int nearestCity = -1;
		double min = Double.MAX_VALUE;
		for (int to : unvisited)
		{
			if (min > graph.weights[from][to])
			{
				min = graph.weights[from][to];
				nearestCity = to;
			}
		}
		return nearestCity;
	}

	public final Solution getSolution()
	{
		if (solution == null)
		{
			optimize();
		}
		return solution;
	}
}

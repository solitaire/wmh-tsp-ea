package optimization.greedy;

import graph.Graph;

import java.util.HashSet;
import java.util.Set;

import optimization.Evaluator;
import optimization.Optimizer;
import testing.Main;

public class Greedy extends Optimizer
{
	private final Graph graph;

	public Greedy(Evaluator evaluator, Graph graph)
	{
		super(evaluator, graph.D);
		this.graph = graph;
	}

	@Override
	public double optimize()
	{
		double length = 0.0;
		final int START_CITY = Main.rand.nextInt(D);
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
			unvisited.remove(nearestCity);
			actualCity = nearestCity;
		}
		while (!unvisited.isEmpty());
		length += graph.weights[actualCity][START_CITY];
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
}

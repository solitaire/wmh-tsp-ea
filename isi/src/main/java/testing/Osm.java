package testing;

import graph.Coords;
import graph.OsmGraph;
import graph.builder.GraphBuilder;

import java.util.Map;

import optimization.BestResult;
import optimization.Optimizer;
import optimization.Solution;
import optimization.ea.EA;
import optimization.greedy.Greedy;
import settings.AlgorithmSettings;

public class Osm
{
	private final static String OSM_MAP_NAME = "europe_poland-gh";

	public static void main(String[] args)
	{
		final OsmGraph graph = GraphBuilder.build(System.in, OSM_MAP_NAME);

		double mutationProbability = AlgorithmSettings.DEFAULT_MUTATION_PROBABILITY;
		double crossoverProbability = AlgorithmSettings.DEFAULT_CROSSOVER_PROBABILITY;
		Optimizer optimizer = new EA(graph, mutationProbability, crossoverProbability);
		if (args.length >= 1)
		{
			if (args[0].equals("greedy"))
			{
				optimizer = new Greedy(graph);
			}
			else
			{
				mutationProbability = Double.parseDouble(args[0]);
			}
		}
		if (args.length >= 2)
		{
			crossoverProbability = Double.parseDouble(args[1]);
		}
		if (args.length >= 3)
		{
			if (args[2].equals("greedystart"))
			{
				optimizer = new EA(graph, mutationProbability, crossoverProbability, true);
			}
		}

		BestResult bestResult = null;
		for (int i = 0; i < AlgorithmSettings.RUNS; i++)
		{
			if (bestResult == null) {
				bestResult = optimizer.optimize();
			}
			else 
			{
				BestResult result = optimizer.optimize();
				if (result.getOptimum() < bestResult.getOptimum())
				{
					bestResult = result;
				}
			}
		}
		System.out.println(bestResult.getOptimum());
		System.out.println(printMappedSolution(graph.getMapping(), bestResult.getSolution()));
	}
	
	private static String printMappedSolution(Map<Integer, Coords> mapping, Solution solution) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < solution.cities.length; i++) {
			result.append(mapping.get(solution.cities[i]));
		}
		return result.toString();
	}
}

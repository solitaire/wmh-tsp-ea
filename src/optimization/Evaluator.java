package optimization;

import graph.Graph;

public class Evaluator
{
	private final int MAX_FUN_EVALS;
	private final Graph graph;
	private int funEvals;
	private Solution best;
	private double bestScore = Double.MAX_VALUE;

	public Evaluator(Graph graph, int maxFunEvals)
	{
		MAX_FUN_EVALS = maxFunEvals;
		this.graph = graph;
	}

	public double evaluate(Solution solution)
	{
		funEvals++;
		double score = 0.0;
		for (int i = 0; i < solution.cities.length - 1; i++)
		{
			score += graph.weights[solution.cities[i]][solution.cities[i + 1]];
		}
		score += graph.weights[solution.cities[solution.cities.length - 1]][solution.cities[0]];
		if (bestScore > score)
		{
			bestScore = score;
			best = solution;
		}
		return score;
	}

	public boolean hasReachedMaxFunEvals()
	{
		return funEvals >= MAX_FUN_EVALS;
	}

	public int getFunEvals()
	{
		return funEvals;
	}

	/** @return True, if a is better than b. */
	public boolean isBetter(Solution a, Solution b)
	{
		return evaluate(a) < evaluate(b);
	}

	public Solution getBestObservedSolution()
	{
		return best;
	}

	public double getBestObservedScore()
	{
		return bestScore;
	}

	public void resetFunEvals()
	{
		funEvals = 0;
	}
}

package optimization;

import graph.Graph;

public abstract class Optimizer
{
	public final int D;
	protected final Graph graph;

	public Optimizer(Graph graph)
	{
		this.D = graph.D;
		this.graph = graph;
	}

	abstract public BestResult optimize();
}

package graph;

import java.util.Map;

public class OsmGraph extends Graph
{
	private final Map<Integer, Coords> mapping;
	
	public OsmGraph(double[][] weights, Map<Integer, Coords> mapping)
	{
		super(weights);
		this.mapping = mapping;
	}

	public final Map<Integer, Coords> getMapping() {
		return mapping;
	}
}

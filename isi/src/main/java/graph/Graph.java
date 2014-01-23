package graph;

import java.io.InputStream;


public class Graph
{
	/** Number of vertices. */
	public final int D;
	/** Weights of edges. */
	public final double[][] weights;
	/** Coords mapping */
	
	public Graph(final double[][] weights) 
	{
		this.weights = weights;
		this.D = weights.length;
	}
	
    public Graph(InputStream stream)
    {
       this.weights = GraphReader.readWeights(stream);
       this.D = weights.length;
    }

	@Override
	public String toString()
	{
		final StringBuilder result = new StringBuilder();
		final String lineSeparator = System.getProperty("line.separator");
		for (int y = 0; y < D; y++)
		{
			for (int x = 0; x < D; x++)
			{
				result.append(weights[x][y]);
				result.append(" ");
			}
			result.append(lineSeparator);
		}
		return result.toString();
	}
}

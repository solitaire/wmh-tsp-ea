package graph.builder;

import graph.Graph;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.GraphHopperAPI;

public class GraphBuilder
{
	public static Graph build(final InputStream in, final String osmMapName) 
	{
		// Sets default locale to always have 1.23 not 1,23
		Locale.setDefault(Locale.US);
		final Scanner scanner = new Scanner(System.in);
		List<Coords> coords = new ArrayList<Coords>();
		while (scanner.hasNext()) 
		{
			coords.add(new Coords(scanner.nextDouble(), scanner.nextDouble()));
		}
		
		int D = coords.size();
		double [][] weights = new double[D][D];
		GraphHopperAPI gh = new GraphHopper().forServer();
		gh.load(osmMapName);
		
		for (int y = 0; y < D; y++)
		{
			for (int x = 0; x < D; x++)
			{
				Coords start = coords.get(y);
				Coords end = coords.get(x);
				GHRequest request = new GHRequest(start.getLatitude(), start.getLongitute(), end.getLatitude(), end.getLongitute()); 
				request.setAlgorithm("astarbi"); 
				GHResponse response = gh.route(request);
				// getDistance returns value in meters, change to kilometers
				weights[x][y] = response.getDistance() / 1000;
			}
		}
		
		return new Graph(weights);
	}
}

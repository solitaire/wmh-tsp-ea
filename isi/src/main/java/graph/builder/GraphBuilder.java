package graph.builder;

import graph.Graph;

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
	public static void main(String[] args)
	{
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
		gh.load("europe_poland-gh");
		
		for (int y = 0; y < D; y++)
		{
			for (int x = 0; x < D; x++)
			{
				Coords start = coords.get(y);
				Coords end = coords.get(x);
				GHRequest request = new GHRequest(start.getLatitude(), start.getLongitute(), end.getLatitude(), end.getLongitute()); 
				request.setAlgorithm("astarbi"); 
				GHResponse response = gh.route(request);
				weights[x][y] = response.getDistance();
			}
		}
		
		Graph graph = new Graph(weights);
		System.out.println(D);
		System.out.println(graph);
	}
}

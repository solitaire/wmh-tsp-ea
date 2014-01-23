package settings;

import java.util.Random;

public class AlgorithmSettings
{
	public final static int SEED = 1;
	public final static Random rand = new Random(SEED);
	public final static double DEFAULT_MUTATION_PROBABILITY = 0.1;
	public final static double DEFAULT_CROSSOVER_PROBABILITY = 0.9;
	public final static int RUNS = 15;
}

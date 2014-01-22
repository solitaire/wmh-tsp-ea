package optimization;

public class BestResult
{
	private final double optimum;
	private final Solution solution;
	
	public BestResult(final double optimum, final Solution solution) {
		this.optimum = optimum;
		this.solution = solution;
	}
	
	public final double getOptimum() {
		return optimum;
	}
	
	public final Solution getSolution() {
		return solution;
	}
}

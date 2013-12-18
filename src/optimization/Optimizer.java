package optimization;

public abstract class Optimizer
{
	public final int D;

	protected final Evaluator evaluator;

	public Optimizer(Evaluator evaluator, int d)
	{
		D = d;
		this.evaluator = evaluator;
	}

	abstract public double optimize();
}

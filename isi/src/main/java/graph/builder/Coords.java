package graph.builder;

class Coords
{
	private final double latitute;
	private final double longitute;

	public Coords(double latitute, double longitude)
	{
		this.latitute = latitute;
		this.longitute = longitude;
	}

	public double getLongitute()
	{
		return longitute;
	}

	public double getLatitude()
	{
		return latitute;
	}
}

package regression;

public abstract class Regression implements IRegression{

	final double[][] approximatedValues;

	public Regression(double[][] values) {
		approximatedValues=values;
	}
	
}

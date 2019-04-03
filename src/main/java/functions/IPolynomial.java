package functions;

public interface IPolynomial {

	double eval(double x);
	
	String toString();

	int getDegree();

	double[] getCoefficients();
}

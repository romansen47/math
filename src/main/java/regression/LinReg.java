package regression;

import functions.IPolynomial;
import matrix.GaussianElimination;

public class LinReg extends Regression{
	
	final functions.IPolynomial polynomial;
	final int kmax;
	final int degree;
	public double distance;
	
	public LinReg(double[][] values, int degree) {
		super(values);
		this.degree=degree+1;
		this.distance=0;
		if (values.length<2) {
			System.err.println("Need 2 rows for x and y values...");
			System.exit(1);
		}
		else if(values[0].length<=this.degree) {
			System.err.println("Degree is to high; otherwise need more values...");
			System.exit(1);
		}
		kmax=values[0].length;
		polynomial = computePolynomial(values);
		for (int i=0;i<values[0].length;i++) {
			distance+=Math.pow(values[1][i]-polynomial.eval(values[0][i]), 2);
		}
	}

	private IPolynomial computePolynomial(double[][] values) {
		
		double[] x=values[0];
		double[] y=values[1];
		double[][] Matrix=new double[degree][degree];
		double[] b=new double[degree];
		
		for (int m=0;m<degree;m++) {
			for (int k=0;k<kmax;k++) {
				b[m]+=Math.pow(x[k], m)*y[k];
			}
		}
		
		for (int m=0;m<degree;m++) {
			for (int j=0;j<degree;j++) {
				for (int k=0;k<kmax;k++) {
					Matrix[m][j]+=Math.pow(x[k],m+j);
				}
			}
		}
		matrix.IMatrixTrix matOp=new matrix.MatrixOperator();
		double[] ans=GaussianElimination.lsolve(Matrix,b);
		return new functions.Polynomial(ans);//matOp.matrixMult(matOp.inverse(Matrix), b));
	}

	@Override
	public IPolynomial getPolynomial() {
		return polynomial;
	}

	@Override
	public double getDistance() {
		return distance;
	}
	
	
	
}

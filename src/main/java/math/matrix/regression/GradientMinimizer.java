/**
 * 
 */
package math.matrix.regression;

import math.matrix.IMatrix;
import math.matrix.Matrix;
import math.matrix.functions.Derivative;
import math.matrix.functions.IDerivative;
import math.matrix.functions.IFunction;

/**
 * @author RoManski
 *
 */
public class GradientMinimizer implements IMinimizer {

	final double stepsize;
	final double precission;
	
	public GradientMinimizer(double eps,double precission) {
		this.stepsize=eps;
		this.precission=precission;
	}
	@Override
	public IMatrix find(IFunction function, IMatrix init) throws Exception {
		double norm=Double.MAX_VALUE;
		IMatrix tmp=new Matrix(init.getValues().clone());
		IDerivative derivative=new Derivative(function);
		IMatrix direction = derivative.jacobiMatrix(tmp);
		while (norm>precission) {
			norm=goAlongMinimizingDirection(derivative,tmp,direction.scaleBy(-1));
		}
		return tmp;
	}

	private double goAlongMinimizingDirection(IDerivative derivative, IMatrix tmp,IMatrix direction) throws Exception {
		IMatrix delta=derivative.jacobi(tmp).value(direction.scaleBy(-1));
		tmp=tmp.add(delta.toUnit().scaleBy(getStepsize()));
		return tmp.norm();
	}
	
	/**
	 * @return the stepsize
	 */
	public final double getStepsize() {
		return stepsize;
	}
	/**
	 * @return the precission
	 */
	public double getPrecission() {
		return precission;
	}
	
}

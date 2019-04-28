package functions;

public class Derivative {
	
	final double feinheit;
	final IFunction fun;
	
	public Derivative(double fein, IFunction fun) {
		this.feinheit=fein;
		this.fun=fun;
	}
	
	public double value(double[] point,double[] direction) {
		double[] delta=new double[point.length];
		for (int i=0;i<point.length;i++) {
			delta[i]=point[i]+feinheit*direction[i];
		}
		return 1.0/feinheit*(fun.value(delta)-fun.value(point));
	}
	
	public double[] Gradient(double[] point){
		double[] Grad=new double[point.length];
		for (int i =0;i<point.length;i++) {
			double[] vec=new double[point.length];
			for (int j=0;j<point.length;j++) {
				vec[j]=0;
			}
			vec[i]=1;
			Grad[i]=value(point,vec);
		}
		return Grad;
	}
	
	public double[][] HesseMatrix(double[] point){
		double[][] Hesse=new double[point.length][point.length];
		for (int i=0;i<point.length;i++) {
			final int j=i;
			IFunction fun=new Function() {
				@Override
				public double value(double[] point) {
					return Gradient(point)[j];
				}
			};
			Derivative Deriv=new Derivative(feinheit,fun);
			Hesse[i]=Deriv.Gradient(point);
		}
		return Hesse;
	}

}

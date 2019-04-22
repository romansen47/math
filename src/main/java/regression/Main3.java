package regression;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import proprietary.StdDraw;

public class Main3 extends regression.Main{
		
	static double[][] vals;
	
	public static void main(String[] args) throws IOException {
		values=readFile(PATH);
		double[][] newvals=centralize(values);
		double min=200000.0;
		//double max=getMax(newvals);
		double a0=0;
		double b0=0;
		double c0=0;
		for (double a=5.0;a<7;a+=0.01) {
			for (double b=0;b<3.14;b+=0.10) {
				for (double c=0.001;c<0.01;c+=0.0001) {
					double val=val(a,b,c,newvals);
					if (val<min) {
						a0=a;
						b0=b;
						c0=c;
						min=val;
					}
				}
			}
		}
		IRegression reg=new LinReg(values,1);
		double[] alpha=reg.getPolynomial().getCoefficients();
		System.out.println(alpha[0]+"+"+alpha[1]+"*x"+" + "+a0+"*sin("+b0+"+"+c0+"*x");

		double[][] valsToPlot=new double[2][2*values[0].length];
		double[][] altInput=new double[2][2*values[0].length];
		for (int i=0;i<values[0].length;i++) {
			altInput[0][i]=values[0][i];
			altInput[1][i]=values[1][i];
			valsToPlot[0][i]=values[0][i];
			valsToPlot[1][i]=reg.getPolynomial().eval(values[0][i])+a0*Math.sin(b0+c0*values[0][i]);
		}
		for (int i=values[0].length;i<2*values[0].length;i++) {
			altInput[0][i]=values[0][values[0].length-1]-values[0].length+i;
			altInput[1][i]=reg.getPolynomial().eval(altInput[0][i]);
			valsToPlot[0][i]=values[0][values[0].length-1]-values[0].length+i;
			valsToPlot[1][i]=reg.getPolynomial().eval(valsToPlot[0][i])+a0*Math.sin(b0+c0*valsToPlot[0][i]);
		}
		IRegression.preparePlot(altInput,2*DIMX,DIMY);
		IRegression.drawInput(altInput);
		StdDraw.setPenRadius(0.01);
        for (int i = 0; i < valsToPlot[0].length-1; i+=1) {
    		StdDraw.setPenColor(Color.RED);
        	StdDraw.line(valsToPlot[0][i],valsToPlot[1][i],valsToPlot[0][i+1],valsToPlot[1][i+1]);
        }
        
	}
	
	private static double val(double a, double b, double c, double[][] values) {
		double ans=0.0;
		for (int i=0;i<values[0].length;i++) {
			ans+=Math.pow(values[1][i]-a*Math.sin(b+c*values[0][i]),2);
		}
		return ans;
	}
	
	private static double[][] getAsinVals(double[][] newvals) {
		double max=getMax(newvals);
		double[][] normvals=normalizeValues(max,newvals);
		double[][] asinvals=new double[2][normvals[0].length];
		asinvals[0]=newvals[0];
		for (int i=0;i<newvals[0].length;i++) {
			asinvals[1][i]=Math.asin(normvals[1][i]);
			System.out.println(newvals[0][i]+", "+asinvals[1][i]);
		}
		return asinvals;
	}

	private static double[][] normalizeValues(double max,double[][] newvals) {
		double[][] normvals=new double[2][newvals[0].length];
		normvals[0]=newvals[0];
		for (int i=0;i<newvals[0].length;i++) {
			normvals[1][i]=newvals[1][i]/max;
		}
		return normvals;
	}

	private static double getMin(double[][] newvals) {
		double min=Math.abs(newvals[1][0]);
		for (int i=0;i<newvals[0].length;i++) {
			min=Math.min(min,Math.abs(newvals[1][i]));
		}
		return min;
	}
	
	private static double getMax(double[][] newvals) {
		double max=Math.abs(newvals[1][0]);
		for (int i=0;i<newvals[0].length;i++) {
			max=Math.max(max,Math.abs(newvals[1][i]));
		}
		return max;
	}

	public static double[][] centralize(double[][] vals) {
		final IRegression linreg=new LinReg(vals,1);
		double[][] newvalues=new double[2][vals[0].length];
		newvalues[0]=vals[0];
		for (int i=0;i<vals[0].length;i++) {
			newvalues[1][i]=vals[1][i]-linreg.getPolynomial().eval(vals[0][i]);
		}
		return newvalues;
	}
}

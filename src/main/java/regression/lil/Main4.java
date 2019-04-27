package regression.lil;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import functions.Function;
import functions.IFunction;
import math.IVector;
import math.MathOp;
import regression.DataSet;
import regression.IRegression;
import regression.LinReg;
import regression.Minimizer;

public class Main4 extends regression.Main{
		
	static double[][] vals;
	static double[][] newvals;
	
	public static void main(String[] args) throws IOException {
		values=readFile(PATH);
		newvals=values;
		double min=200000.0;
		double a0=0;
		double b0=0;
		double c0=0;
//		for (double a=5.7;a<5.8;a+=0.01) {
//			for (double b=2.2;b<2.5;b+=0.01) {
//				for (double c=0.0027;c<0.0035;c+=0.0001) {
//					double val=val(a,b,c,newvals);
//					if (val<min) {
//						a0=a;
//						b0=b;
//						c0=c;
//						min=val;
//					}
//					//System.out.println(a+", "+b+", "+c+" -value="+val);
//				}
//			}
//		}
//		IRegression reg=new LinReg(values,1);
//		double[] alpha=reg.getPolynomial().getCoefficients();
//		System.out.println(alpha[0]+"+("+alpha[1]+")*x"+" + "+a0+"*sin("+b0+"+"+c0+"*x)");

//		double[][] valsToPlot=new double[2][2*values[0].length];
//		double[][] altInput=new double[2][2*values[0].length];
//		for (int i=0;i<values[0].length;i++) {
//			altInput[0][i]=values[0][i];
//			altInput[1][i]=values[1][i];
//			valsToPlot[0][i]=values[0][i];
//			valsToPlot[1][i]=reg.getPolynomial().eval(values[0][i])+a0*Math.sin(b0+c0*values[0][i]);
//		}
//		for (int i=values[0].length;i<2*values[0].length;i++) {
//			altInput[0][i]=values[0][values[0].length-1]-values[0].length+i;
//			altInput[1][i]=reg.getPolynomial().eval(altInput[0][i]);
//			valsToPlot[0][i]=values[0][values[0].length-1]-values[0].length+i;
//			valsToPlot[1][i]=reg.getPolynomial().eval(valsToPlot[0][i])+a0*Math.sin(b0+c0*valsToPlot[0][i]);
//		}
//		IRegression.preparePlot(altInput,2*DIMX,DIMY);
//		IRegression.drawInput(altInput);
//		StdDraw.setPenRadius(0.01);
//        for (int i = 0; i < valsToPlot[0].length-1; i+=1) {
//    		StdDraw.setPenColor(Color.RED);
//        	StdDraw.line(valsToPlot[0][i],valsToPlot[1][i],valsToPlot[0][i+1],valsToPlot[1][i+1]);
//        }
        
        final IFunction fun=new Function() {
        	@Override
        	public double value(double[] point) {
        		return val(point[0],point[1],point[2],point[3],point[4],newvals)*1.e-10;
        	}
        };
        IRegression rg=new LinReg(newvals, 1);
        double a=20;//rg.getPolynomial().getCoefficients()[0];
        double b=0;//rg.getPolynomial().getCoefficients()[1];
        double[] coeffs=new Minimizer(fun).find(new double[] {a,b,7,3,0.0026580});
        //System.out.println(coeffs[0]+","+coeffs[1]+","+coeffs[2]+","+coeffs[3]+","+coeffs[4]);
        System.out.println("Wert: "+val(a, b,5.750578,2.42977512,0.002701427,newvals));
        System.out.println("Wert: "+val(coeffs[0], coeffs[1], coeffs[2],
        		coeffs[3], coeffs[4], newvals));
        double correlation=correlation(values[0],values[1]);
        System.out.println("Korrelationskoeffizient = "+correlation);
        
        IFunction reg=new Function(){
    		@Override
    		public double value(double[]input) {
    			return coeffs[0]+coeffs[1]*input[0]
    					+coeffs[2]*Math.sin(coeffs[3]+coeffs[4]*input[0]);
    		}
    	};
    	
        printDataToXml(coeffs,newvals,reg,correlation);
        
	}

	private static void printDataToXml(double[] coeffs, double[][] newvals2,IFunction fun, double correlation) throws IOException {
		double length = (int) (newvals2[0][newvals2[0].length-1]);
		DataSet[] data=new DataSet[(int)length];
		for (int i=0;i<newvals2[0].length-1;i++) {
			data[(int)newvals2[0][i]]=new DataSet((int)newvals2[0][i],newvals2[1][i],fun);
			for (int j=(int) newvals2[0][i]+1;j<newvals2[0][i+1];j++) {
				data[j]=new DataSet(j,null,fun);
			}
		}

		for (int i=0;i<data.length;i++) {
			System.out.println(data[i].toString()+"\r");
		}
//		BufferedWriter writer = new BufferedWriter(new FileWriter("outpt.csv"));
//		for (int i=0;i<2000;i++) {
//			writer.write(data[i].toString()+"\r");
//		}
//		BufferedWriter writer2 = new BufferedWriter(new FileWriter("outpt2.csv"));
//		for (int i=2001;i<data.length;i++) {
//			String test=data[i].toString()+"\r";
//			writer2.write(test);
//		}
		int lastday=data[0].
	    System.out.println(fun.value(input));
	}

	private static double correlation(double[] ds, double[] ds2) {
		final IVector mathob=new MathOp(1.e-5);
		return mathob.ScalarProduct(ds, ds2)/(mathob.MagnitudeOfVector(ds)*mathob.MagnitudeOfVector(ds2));
	}

	
	private static double val(double a, double b, double c, double d, double e, double[][] values) {
		double ans=0.0;
		for (int i=0;i<values[0].length;i++) {
			ans+=Math.pow(values[1][i]-a-b*values[0][i]-c*Math.sin(d+e*values[0][i]),2);
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

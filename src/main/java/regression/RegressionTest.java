package regression;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import proprietary.StdDraw;

public class RegressionTest {

	static int xm;
	
	final static int degree=40;
	
	static double[][] values;
	
	public static void main(String[] args) throws IOException {
		values=readFile("src/main/resources/test.csv");
		List<IRegression> LinRegs=new ArrayList<>();
		for (int i=0;i<degree;i++) {
			IRegression tmpReg=new LinReg(values,i);
			LinRegs.add(tmpReg);
			System.out.println("Regression "+i+"-ter Ordnung: Abstand="+tmpReg.getDistance());
		}
		solveAndDraw(degree);		
	}
	
	public static void solveAndDraw(int degr) {
		
		final IRegression reg=new LinReg(values,degr);
		xm=reg.getPolynomial().getCoefficients().length;
		StdDraw stddraw=new StdDraw();
		stddraw.setCanvasSize(2000, 1500);
		StdDraw.setXscale(0, values[0][values[0].length-1]);
		StdDraw.setYscale(0.0, 50.0);

		StdDraw.setPenRadius(0.01);
        for (int i = 0; i < values[0].length-1; i+=5) {
        	StdDraw.point(values[0][i], values[1][i]);
        }
        
		StdDraw.setPenColor(Color.RED);
        double prec=0.1;
        for (double i = 0.0; i < values[0].length; i+=1/prec) {
        	StdDraw.line(i, reg.getPolynomial().eval(i),i+1/prec, reg.getPolynomial().eval(i+1/prec));
        }
		
        System.out.println("Regression "+degr+"-ten Grades");
	}
	
	static double[][] readFile(String string) throws IOException {
		
		List<double[]> values = new ArrayList<>();
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(string));
		String line="";
		while ((line=br.readLine())!=null) {
			String[] parts=line.split(";");
			double[] tmp=new double[2];
			tmp[0]=Double.parseDouble(parts[0]);
			tmp[1]=Double.parseDouble(parts[1]);
			values.add(tmp);
		}
		double[][] ans=new double[2][values.size()];
		for(int i=0;i<values.size();i++) {
			ans[0][i]=values.get(i)[0];
			ans[1][i]=values.get(i)[1];
		}
		return ans;
	}
	
}

package regression;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import proprietary.StdDraw;

public class RegressionTest {

	final static String path="src/main/resources/test.csv";
	
	static int xm;
	
	final static int degree=43;
	
	static double[][] values;
	
	public static void main(String[] args) throws IOException {
		values=readFile(path);
		List<IRegression> LinRegs=new ArrayList<>();
		for (int i=0;i<degree;i++) {
			IRegression tmpReg=new LinReg(values,i);
			LinRegs.add(tmpReg);
		}
		List<Integer> sort=evaluateMoveList(LinRegs);
		for (int i=0;i<degree;i++) {
			System.out.println("Regression "+LinRegs.get(i).getPolynomial().getDegree()
					+"-ter Ordnung: Abstand="+LinRegs.get(i).getDistance());
		}
		solveAndDraw(LinRegs.get(0));		
	}
	
	public static void solveAndDraw(IRegression reg) {
		
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
        System.out.println("");
        System.out.println("Baste Naeherung Regression "+reg.getPolynomial().getDegree()+"-ten Grades geplottet");
	}
	
	private static List<Integer> evaluateMoveList(List<IRegression> linRegs) {
		class SortByEval implements Comparator<IRegression> {
			// Used for sorting in ascending order
			// of
			// roll number
			@Override
			public int compare(IRegression o1, IRegression o2) {
				return (int)(o1.getDistance()-o2.getDistance());
			}
		}
		Collections.sort(linRegs, new SortByEval());
		List<Integer> sortedMoves = new ArrayList<>();
		for (IRegression reg : linRegs) {
			sortedMoves.add(new Integer((int) reg.getDistance()));
		}
		return sortedMoves;
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

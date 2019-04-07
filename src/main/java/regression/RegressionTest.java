package regression;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import proprietary.StdDraw;

public class RegressionTest {

	final static String path="src/main/resources/test2.csv";
	
	static int xm;
	
	final static int degree=30;
	
	static double[][] values;
	
	public static void main(String[] args) throws IOException {
		
		values=readFile(path);
		
		double min=values[1][0];
		double max=values[1][0];
		
		for (int i=1;i<values[0].length;i++) {
			if (min>values[1][i]) {
				min=values[1][i];
			}
			if (max<values[1][i]) {
				max=values[1][i];
			}
		}
		
		List<IRegression> LinRegs=new ArrayList<>();
		for (int i=0;i<degree;i++) {
			IRegression tmpReg=new LinReg(values,i);
			LinRegs.add(tmpReg);
		}
		evaluateMoveList(LinRegs);
		for (int i=0;i<degree;i++) {
			System.out.println("Regression "+LinRegs.get(i).getPolynomial().getDegree()
					+"-ter Ordnung: Abstand="+LinRegs.get(i).getDistance());
		}
		StdDraw stddraw=new StdDraw();
		stddraw.setCanvasSize(1500, 1000);
		StdDraw.setXscale(0, values[0][values[0].length-1]);
		StdDraw.setYscale(min-10,max+10);

		StdDraw.setPenRadius(0.01);
		for (int i = 0; i < values[0].length-1; i+=1) {
        	StdDraw.point(values[0][i], values[1][i]);
        }
		

        System.out.println("");
		solveAndDraw(LinRegs.get(0),Color.RED);
	}
	
	public static void solveAndDraw(IRegression reg,Color col) {
		xm=reg.getPolynomial().getCoefficients().length;
		StdDraw.setPenRadius(0.01);
        double prec=10.0;
        for (int i = 0; i < values[0].length-1; i+=prec) {
    		StdDraw.setPenColor(col);
        	StdDraw.line(values[0][i], reg.getPolynomial().eval(values[0][i]),
        					values[0][i]+prec, reg.getPolynomial().eval(values[0][i]+prec));
        }
        System.out.println("Regression "+reg.getPolynomial().getDegree()+"-ten Grades geplottet");
	}
	
	private static List<Integer> evaluateMoveList(List<IRegression> linRegs) {
		class SortByEval implements Comparator<IRegression> {
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
		LocalDate firstDate=null;
		while ((line=br.readLine())!=null) {
			String[] parts=line.split(";");
			String[] tmpdate=parts[0].split("-");
			LocalDate date=LocalDate.of(Integer.parseInt(tmpdate[0]),
										Integer.parseInt(tmpdate[1]),
										Integer.parseInt(tmpdate[2]));
			if (firstDate==null) {
				firstDate=date;
			}
			double[] tmp=new double[2];
			tmp[0]=firstDate.until(date,ChronoUnit.DAYS);
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

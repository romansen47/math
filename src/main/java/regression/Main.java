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


public class Main {

	static final String PATH="src/main/resources/test2.csv";
	static final int DEGREE=11;
	static final int DIMX=1000;
	static final int DIMY=700;
	static double[][] values;
	
	public static void main(String[] args) throws IOException {
		
		values=readFile(PATH);
		List<IRegression> linRegs=new ArrayList<>();
		for (int i=0;i<DEGREE;i++) {
			IRegression tmpReg=new LinReg(values,i);
			linRegs.add(tmpReg);
		}
		evaluateList(linRegs);
		printSortedList(linRegs);
		IRegression.preparePlot(values,DIMX,DIMY);
		IRegression.drawInput(values);
        System.out.println("");
		linRegs.get(0).solveAndDraw(Color.RED,values,10.0);
	}

	private static void printSortedList(List<IRegression> linRegs) {
		for (int i=0;i<DEGREE;i++) {
			System.out.println("Regression "+linRegs.get(i).getPolynomial().getDegree()
					+"-ter Ordnung: Abstand="+linRegs.get(i).getDistance());
		}
	}
	
	private static List<Integer> evaluateList(List<IRegression> linRegs) {
		class SortByEval implements Comparator<IRegression> {
			@Override
			public int compare(IRegression o1, IRegression o2) {
				return (int)(o1.getDistance()-o2.getDistance());
			}
		}
		Collections.sort(linRegs, new SortByEval());
		List<Integer> sortedMoves = new ArrayList<>();
		for (IRegression reg : linRegs) {
			sortedMoves.add(((int) reg.getDistance()));
		}
		return sortedMoves;
	}
	
	static double[][] readFile(String string) throws IOException {
		List<double[]> values = new ArrayList<>();
		@SuppressWarnings("resource")
		final BufferedReader br = new BufferedReader(new FileReader(string));
		String line="";
		LocalDate firstDate=null;
		try {
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
		}
		finally {
			br.close();
		}
		double[][] ans=new double[2][values.size()];
		for(int i=0;i<values.size();i++) {
			ans[0][i]=values.get(i)[0];
			ans[1][i]=values.get(i)[1];
		}
		return ans;
	}
	
}

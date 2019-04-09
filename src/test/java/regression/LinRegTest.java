package regression;

import java.awt.Color;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LinRegTest {

	final Random random=new Random();
	final int finalDegree=11;
	double[][] values;
	final double tolerance=25;
	final double correctness=0.05;
	final int dimsX=1000;
	final int dimsY=700;
	
	@SuppressWarnings("unused")
	@Test
	public void linRegTest() {
		
		for (int degree=1;degree<=finalDegree;degree++) {
			values=new double[2][degree];
			for (int i=0;i<degree;i++) {
				values[0][i]=i;
				values[1][i]=random.nextInt(10);
			}
			IRegression reg=new LinReg(values,degree-1);
			try {
				IRegression.preparePlot(values, dimsX, dimsY);
				IRegression.drawInput(values);
				reg.solveAndDraw(Color.RED, values,correctness);
			} catch( Exception e) {
				e.printStackTrace();
			}
			assertTrue(reg.getDistance()<tolerance);
			System.out.println("Guete der Regression "+(degree-1)+"-ter Ordnung: "+reg.getDistance()+". ");
		}
		
	}
}

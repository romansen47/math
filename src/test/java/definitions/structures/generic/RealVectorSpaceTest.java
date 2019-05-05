package definitions.structures.generic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import definitions.structures.abstr.IVec;

public class RealVectorSpaceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Throwable {

		Random r=new Random();
		double[][] matrix=new double[100][100];
		for (int i=0;i<100;i++) {
			for (int j=0;j<100;j++) {
				matrix[i][j]=r.nextDouble() % 0.2;
			}
		}
		IFiniteDimensionalLinearMapping map=Generator.getGenerator().getFiniteDimensionalLinearMapping(matrix);

		// matrix=map.getGenericMatrix();
		
		double[] input=new double[100];
		for (int i=0;i<100;i++) {
			input[i]=r.nextDouble() % 1.0;
		}
		
		IVec output = map.get(new RealVec(input));
		
		double x=0.005*Generator.getGenerator().getFiniteDimensionalVectorSpace(100).norm(output);
		
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
	}

}

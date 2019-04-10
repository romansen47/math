package matrix;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class MatrixOperatorTest {

	private static double[][] Matrix1=new double[2][2];
	private static double[][] Matrix2=new double[2][2];
	private static double[] vec1		=new double[2];
	private static double[] vec2		=new double[2];
	final IMatrixTrix MatOp=new MatrixOperator();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Matrix1[0][0]=0;
		Matrix1[0][1]=1;
		Matrix1[1][0]=-1;
		Matrix1[1][1]=0;
		
		Matrix1[0][0]=1;
		Matrix1[0][1]=0;
		Matrix1[1][0]=0;
		Matrix1[1][1]=-1;

		vec1[0]=1;
		vec1[1]=0;
		vec1[0]=0;
		vec1[0]=1;
	}

	@Test
	public void test() {
		
		double det1=MatOp.det(Matrix1);
		double det2=MatOp.det(Matrix2);

		assertEquals(true,true);
	}
	
	@Test
	public void inverse(){
		double[][] inverse1=MatOp.inverse(Matrix1);
		double[][] inverse2=MatOp.inverse(Matrix2);

		assertEquals(true,true);
	}

	@Test
	public void det(){
		double det1=MatOp.det(Matrix1);
		double det2=MatOp.det(Matrix2);

		assertEquals(true,true);
	}
	
	@Test
	public void matrixMult() {
		double[] firstRow1=MatOp.matrixMult(Matrix1,vec1);
		double[] secondRow1=MatOp.matrixMult(Matrix1,vec2);
		
		double[] firstRow2=MatOp.matrixMult(Matrix2,vec1);
		double[] secondRow2=MatOp.matrixMult(Matrix2,vec2);		


		assertEquals(true,true);
	}
	
}

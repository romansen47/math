package matrix;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import math.matrix.IMatrix;
import math.matrix.Identity;
import math.matrix.Matrix;

public class MatrixTest {
	
	static double[][] A;
	static double[][] B;
	static IMatrix mat;
	static IMatrix mat2;
	static IMatrix X;
	static IMatrix Y;
	static IMatrix Z;
	static IMatrix col;
	static IMatrix row;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		A = new double[][]{{1,2,3,4},{0,2,3,-2},{0,-1,2,2}};
		
		B = new double[][]{{1,0,1},{0,1,0},{-1,0,1}};
		
		mat=new Matrix(B);
		
		mat2=new Matrix(A);

		//IMatrix row=mat.getRow(1);
		
		IMatrix col=mat.getColumn(2);
		
		IMatrix transposed=mat.transposed();
		
		double a=mat.det();
		
		IMatrix Y=mat.inverse();
		
		IMatrix Z=Y.multiplyWith(col);
		
		IMatrix U=mat.lsolve(new Identity(mat.getLength()));
		
	}

	@Test
	public void test() throws Exception {
		Assert.assertTrue(true);
	}

}

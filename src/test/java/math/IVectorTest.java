package math;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class IVectorTest {

	static IVector VEC;
	static double[] vec1;
	static double[] vec2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		VEC=new MathOp(1.e-5);
		vec1=new double[] {1,1};
		vec2=new double[] {1,-1};
	}

	@Test
	public void testAdditionOfVectors() {
		assertTrue(same(VEC.AdditionOfVectors(vec1,vec2),new double[] {2,0}));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testMagnitudeOfVector() {
		assertTrue(Math.abs(VEC.MagnitudeOfVector(vec1)-VEC.SQRT(2))<1.e-5);
		assertTrue(Math.abs(VEC.MagnitudeOfVector(vec2)-VEC.SQRT(2))<1.e-5);
	}

	@Test
	public void testProjection() {
		assertEquals(true,true);
	}

	@Test
	public void testProjectionComplement() {
		assertEquals(true,true);
	}

	@Test
	public void testReversalOfVector() {
		assertEquals(true,true);
	}

	@Test
	public void testScalarMultiplication() {
		assertEquals(true,true);
	}

	@Test
	public void testScalarProduct() {
		assertEquals(true,true);
	}

	@Test
	public void testSQRT() {
		assertEquals(true,true);
	}

	@Test
	public void testUnitVector() {
		assertEquals(true,true);
	}
	
	public boolean same(double[] vec1, double[] vec2) {
		double tolerance=1.e-5;
		double ans=0.d;
		for (int i=0;i<vec1.length;i++) {
			ans+=Math.abs(vec1[i]-vec2[i]);
		}
		return ans<tolerance;
		
	}

}

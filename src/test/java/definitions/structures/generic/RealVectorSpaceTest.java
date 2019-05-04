package definitions.structures.generic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import definitions.structures.abstr.IVec;

public class RealVectorSpaceTest {

	final static int dim=2;
	static IFiniteDimensionalVectorSpace R;
	static IFiniteDimensionalVectorSpace P;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Throwable {

		R=Generator.getFiniteDimensionalVectorSpace(dim);
		P=Generator.getFiniteDimensionalVectorSpace(dim+1);
		
		IVec a=new RealVec(new double[] {1,2,3});
		IVec b=new RealVec(new double[] {-2,1,3});
		IVec strA=P.normalize(a);
		IVec strB=P.normalize(b);
		boolean elementOf=strA.elementOf(R);
		boolean contains=P.contains(strB);
		
		Map<RealVec, Map<RealVec, Double>> mat = new HashMap<>();
		List<IVec> baseP=P.getGenericBase();
		IVec e1=baseP.get(0);
		IVec e2=baseP.get(1);
		IVec e3=baseP.get(2);

		List<IVec> baseR=R.getGenericBase();
		IVec b1=baseR.get(0);
		IVec b2=baseR.get(1);
		
		Map<RealVec,Double> v1=new HashMap<>();

		v1.put((RealVec) b1,1.);
		v1.put((RealVec) b2,2.);
		

		Map<RealVec,Double> v2=new HashMap<>();
		
		v2.put((RealVec) b1,-2.);
		v2.put((RealVec) b2,-2.);
		

		Map<RealVec,Double> v3=new HashMap<>();
		
		v3.put((RealVec) b1,1.);
		v3.put((RealVec) b2,-1.);

		mat.put((RealVec) e1,v1);
		mat.put((RealVec) e2,v2);
		mat.put((RealVec) e3,v3);
		
		IFiniteDimensionalLinearMapping map=new FiniteDimensionalLinearMapping(P, R, mat);

		IVec ans=map.get(e1);
		IVec ans2=map.get(e2);
		
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
	}

}

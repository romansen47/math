package definitions.structures.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import definitions.structures.abstr.IVec;

public class Generator {

	private static Generator generator=null;
	
	private Generator() {
	}
	
	public static Generator getGenerator() {
		if (generator==null) {
			generator=new Generator();
		}
		return generator;
	}
	
	private static Map<Integer, IFiniteDimensionalVectorSpace> instance = new HashMap<>();

	public IFiniteDimensionalVectorSpace getFiniteDimensionalVectorSpace(int dim) throws Throwable {
		if (!instance.containsKey(dim)) {
			final List<RealVec> basetmp = new ArrayList<RealVec>();
			for (int i = 0; i < dim; i++) {
				basetmp.add(new RealVec(dim));
				((RealVec) basetmp.get(i)).getCoordinates().put(basetmp.get(i), 1.);
			}
			for (int i = 0; i < dim; i++) {
				for (int j = 0; j < dim; j++) {
					if (i != j) {
						((RealVec) basetmp.get(i)).getCoordinates().put(basetmp.get(j), 0.);
					}
				}
			}
			instance.put(Integer.valueOf(dim), new RealFiniteDimensionalSpace(basetmp));
		}
		return instance.get(dim);
	}
	
	public IFiniteDimensionalLinearMapping getFiniteDimensionalLinearMapping(double[][] genericMatrix) throws Throwable {
		int dimSource=genericMatrix[0].length;
		int dimTarget=genericMatrix.length;
		IFiniteDimensionalVectorSpace source=getFiniteDimensionalVectorSpace(dimSource);
		IFiniteDimensionalVectorSpace target=getFiniteDimensionalVectorSpace(dimTarget);
		Map<RealVec,Map<RealVec,Double>> coordinates=new HashMap<>();
		int i=0;
		for (RealVec vec1:source.getGenericBase()) {
			int j=0;
			Map<RealVec,Double> tmp=new HashMap<>();
			for (RealVec vec2:target.getGenericBase()) {
				tmp.put(vec2,genericMatrix[j][i]);
				j++;
			}
			coordinates.put(vec1,tmp);
			i++;
		}
		if (dimSource!=dimTarget) {
			return new FiniteDimensionalLinearMapping(source,target,coordinates);
		}
		else {
			Endomorphism ans=new LinearSelfMapping(source,coordinates);
			if (ans.det()==0) {
				return ans;
			}
			else {
				return (Isomorphism)ans;
			}
		}
	}

}
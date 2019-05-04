package definitions.structures.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import definitions.structures.abstr.IVec;

public class Generator {

	static Map<Integer, IFiniteDimensionalVectorSpace> instance = new HashMap<>();;

	public static IFiniteDimensionalVectorSpace getFiniteDimensionalVectorSpace(int dim) throws Throwable {
		if (!instance.containsKey(dim)) {
			final List<IVec> basetmp = new ArrayList<IVec>();
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

}

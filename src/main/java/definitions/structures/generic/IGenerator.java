package definitions.structures.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IGenerator {

	Map<Integer, IFiniteDimensionalVectorSpace> getCachedSpaces();

	default IFiniteDimensionalVectorSpace getFiniteDimensionalVectorSpace(int dim) throws Throwable {
		if (!getCachedSpaces().containsKey(dim)) {
			final List<RealVec> basetmp = new ArrayList<RealVec>();
			for (int i = 0; i < dim; i++) {
				basetmp.add(new RealVec(dim));
				basetmp.get(i).getCoordinates().put(basetmp.get(i), 1.);
			}
			for (int i = 0; i < dim; i++) {
				for (int j = 0; j < dim; j++) {
					if (i != j) {
						basetmp.get(i).getCoordinates().put(basetmp.get(j), 0.);
					}
				}
			}
			getCachedSpaces().put(Integer.valueOf(dim), new RealFiniteDimensionalSpace(basetmp));
		}
		return getCachedSpaces().get(dim);
	}

	default IFiniteDimensionalLinearMapping getFiniteDimensionalLinearMapping(double[][] genericMatrix)
			throws Throwable {
		final int dimSource = genericMatrix[0].length;
		final int dimTarget = genericMatrix.length;
		final IFiniteDimensionalVectorSpace source = getFiniteDimensionalVectorSpace(dimSource);
		final IFiniteDimensionalVectorSpace target = getFiniteDimensionalVectorSpace(dimTarget);
		final Map<RealVec, Map<RealVec, Double>> coordinates = new HashMap<>();
		int i = 0;
		for (final RealVec vec1 : source.getGenericBase()) {
			int j = 0;
			final Map<RealVec, Double> tmp = new HashMap<>();
			for (final RealVec vec2 : target.getGenericBase()) {
				tmp.put(vec2, genericMatrix[j][i]);
				j++;
			}
			coordinates.put(vec1, tmp);
			i++;
		}
		if (dimSource != dimTarget) {
			return new FiniteDimensionalLinearMapping(source, target, coordinates);
		} else {
			final Endomorphism ans = new LinearSelfMapping(source, coordinates);
			if (ans.det() == 0) {
				return ans;
			} else {
				return ans;
			}
		}
	}

}

package definitions.structures.generic;

import java.util.HashMap;
import java.util.Map;

import definitions.structures.abstr.IVec;

public class InvertibleFiniteDimensionalLinearMapping extends FiniteDimensionalLinearMapping implements Isomorphism {

	public InvertibleFiniteDimensionalLinearMapping(IFiniteDimensionalVectorSpace source,
			IFiniteDimensionalVectorSpace target, Map<RealVec, Map<RealVec, Double>> linearity) throws Throwable {
		super(source, target, linearity);
		if (det() == 0) {
			throw new Throwable();
		}
	}

	@Override
	public Isomorphism getInverse() throws Throwable {
		final Map<RealVec, Map<RealVec, Double>> coordinates = new HashMap<>();
		for (final IVec vec1 : getSource().getGenericBase()) {
			for (final IVec vec2 : getTarget().getGenericBase()) {
				final Map<RealVec, Double> tmp = new HashMap<RealVec, Double>();
				tmp.put((RealVec) vec1, getLinearity().get(vec1).get(vec2));
				coordinates.put((RealVec) vec2, tmp);
			}
		}
		return (new InvertibleFiniteDimensionalLinearMapping(getTarget(), getSource(), coordinates));
	}

	@Override
	public double det() {
		// TODO Auto-generated method stub
		return 0;
	}

}

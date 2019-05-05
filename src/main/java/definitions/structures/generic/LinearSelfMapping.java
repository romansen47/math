package definitions.structures.generic;

import java.util.Map;

public class LinearSelfMapping extends FiniteDimensionalLinearMapping implements Endomorphism {

	public LinearSelfMapping(IFiniteDimensionalVectorSpace source, Map<RealVec, Map<RealVec, Double>> linearity)
			throws Throwable {
		super(source, source, linearity);
	}

	@Override
	public double det() {
		// TODO Auto-generated method stub
		return 0;
	}

}

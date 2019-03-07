package math;

public interface IVector {

	default double[] AdditionOfVectors(double[] VEC1, double[] VEC2) {
		final double[] addarrays = new double[2];
		addarrays[0] = VEC1[0] + VEC2[0];
		addarrays[1] = VEC1[1] + VEC2[1];
		return addarrays;
	}

	default double MagnitudeOfVector(double[] VEC) {
		return this.SQRT(this.ScalarProduct(VEC, VEC));
	} /* Usage of native Math.sqrt-function weirdly lacks precision */

	default double[] Projection(double[] X, double[] Y) {
		return this.ScalarMultiplication(this.ScalarProduct(X, Y),
				this.ScalarMultiplication(1 / this.MagnitudeOfVector(Y), Y));
	}

	default double[] ProjectionComplement(double[] X, double[] Y) {
		return this.AdditionOfVectors(X, this.ReversalOfVector(this.Projection(X, Y)));
	}

	default double[] ReversalOfVector(double[] VEC) {
		return this.ScalarMultiplication(-1, VEC);
	}

	default double[] ScalarMultiplication(double skalar, double[] VECTOR) {
		final double[] skalarmult = new double[2];
		skalarmult[0] = skalar * VECTOR[0];
		skalarmult[1] = skalar * VECTOR[1];
		return skalarmult;
	}

	default double ScalarProduct(double[] VEC1, double[] VEC2) {
		final double skalprod = VEC1[0] * VEC2[0] + VEC1[1] * VEC2[1];
		return skalprod;
	}

	default double SQRT(double Square) {
		return (Math.sqrt(Square));
		/*
		 * double tmp=Square/2; while
		 * (Abs(tmp*tmp-Square)>Config.SquareRootCorrectness){ tmp=0.5*(tmp+Square/tmp);
		 * } return tmp;
		 */
	}

	default double[] UnitVector(double[] VEC) {
		return this.ScalarMultiplication(1 / this.MagnitudeOfVector(VEC), VEC);
	}

}

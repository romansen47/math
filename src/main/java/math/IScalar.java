package math;

public interface IScalar {

	default public double Abs(double Scalar) {
		if (Scalar >= 0)
			return Scalar;
		else
			return -Scalar;
	}

	default public double SignumFunction(double doub) {
		if (doub == 0)
			return 0;
		else {
			if (doub < 0)
				return -1;
			else
				return 1;
		}
	}
}

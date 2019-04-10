package math;

public class MathOp implements IMathOp {

	private static IMathOp instance = null;

	public static IMathOp getInstance() {
		if (MathOp.instance == null) {
			throw new NullPointerException();
		}
		return MathOp.instance;
	}

	public static IMathOp getInstance(double squareRootCorrectness) {
		if (MathOp.instance == null) {
			return new MathOp(squareRootCorrectness);
		}
		return MathOp.instance;
	}

	private final double squareRootCorrectness;

	MathOp(double squareRootCorrectness) {
		this.squareRootCorrectness = squareRootCorrectness;
	}

	public double getSquareRootCorrectness() {
		return squareRootCorrectness;
	}

}

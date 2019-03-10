package math;

public class MathOp implements IMathOp {

	private static IMathOp instance = null;

	public static IMathOp getInstance() {
		if (MathOp.instance == null) {
			throw new NullPointerException();
		}
		return MathOp.instance;
	}

	public static IMathOp getInstance(double SquareRootCorrectness) {
		if (MathOp.instance == null) {
			return new MathOp(SquareRootCorrectness);
		}
		return MathOp.instance;
	}

	final private double SquareRootCorrectness;

	private MathOp(double SquareRootCorrectness) {
		this.SquareRootCorrectness = SquareRootCorrectness;
	}

	public double getSquareRootCorrectness() {
		return this.SquareRootCorrectness;
	}

}

package math;

public class MathOp implements IMathOp {

	private static IMathOp instance=null;
	
	final private double SquareRootCorrectness;
	
	private MathOp(double SquareRootCorrectness) {
		this.SquareRootCorrectness=SquareRootCorrectness;
	}

	public static IMathOp getInstance(double SquareRootCorrectness) {
		if (instance==null) {
			IMathOp inst=new MathOp(SquareRootCorrectness);
			return inst;
		}
		return instance;
	}
	
	public static IMathOp getInstance() {
		if (instance==null) {
			throw new NullPointerException();
		}
		return instance;
	}
	
	public double getSquareRootCorrectness() {
		return SquareRootCorrectness;
	}

}

package matrix;

public class MatrixTest {

	public static void main(String[] args) {
		
		MatrixOperator matOp=new MatrixOperator();
		
		double[][] Matrix=new double[2][2];

		Matrix[0][0]=0;
		Matrix[0][1]=1;
		Matrix[1][0]=1;
		Matrix[1][1]=0;
		
		double det=matOp.det(Matrix);
		
		double[][] inverse = matOp.inverse(Matrix);
		
		System.out.println("");
		
	}
	
}

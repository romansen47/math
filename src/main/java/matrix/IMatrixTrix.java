package matrix;

public interface IMatrixTrix {

	default double[] matrixMult(double[][] Matrix,double[] vector) {
		double[] y=new double[Matrix.length];
		for (int i=0;i<Matrix.length;i++) {
			double tmp=0;
			for (int j=0;j<Matrix[0].length;j++) {
				tmp+=Matrix[i][j]*vector[j];
			}
			y[i]=tmp;
		}
		return y;
	}
	
	// Only for quadratic matrices!!!
	default double[][] adjointMatrix(double[][] Matrix,int a,int b){
		int k=Matrix.length;
		double[][] adj=new double[k-1][k-1];
		for (int i=0;i<a;i++) {
			for (int j=0;j<b;j++) {
				adj[i][j]=Matrix[i][j];
			}
			for (int j=b+1;j<k;j++) {
				adj[i][j-1]=Matrix[i][j];
			}
		}
		for (int i=a+1;i<k;i++) {
			for (int j=0;j<b;j++) {
				adj[i-1][j]=Matrix[i][j];
			}
			for (int j=b+1;j<k;j++) {
				adj[i-1][j-1]=Matrix[i][j];
			}
		}
		return adj;
	}
	
	default double det(double[][] Matrix) {
		double det=0;
		if (Matrix.length==1) {
			return Matrix[0][0];
		}
		for (int i=0;i<Matrix.length;i++) {
			double[][] adj=adjointMatrix(Matrix,i,0);
			if (i%2==0) {
				det+=det(adj)*Matrix[i][0];
			}
			else {
				det+=-det(adj)*Matrix[i][0];
			}
		}
		return det;
	}
	
	default double[][] inverse(double[][] Matrix){
		int k=Matrix.length;
		double[][] inv=new double[k][k];
		double det;
		try {
			det=1.0/det(Matrix);
		}
		catch(Exception e) {
			System.err.println("Division durch 0!");
			return null;
		}
		for (int i=0;i<k;i++) {
			for (int j=0;j<k;j++) {
				inv[i][j]=Math.pow(-1,(double)i+(double)j)*det(adjointMatrix(Matrix,j,i))*det;
			}
		}
		return inv;
	}
	
}

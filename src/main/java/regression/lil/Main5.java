package regression.lil;

import java.awt.Color;
import java.io.IOException;

import functions.Function;
import functions.IFunction;
import math.IVector;
import math.MathOp;
import proprietary.StdDraw;
import regression.DataSet;
import regression.IRegression;
import regression.LinReg;
import regression.Minimizer;

public class Main5 extends regression.Main {

	static double[][] vals;
	static double[][] newvals;

	public static void main(String[] args) throws IOException {

		values = readFile(PATH);
		newvals = values;

		final IFunction fun = new Function() {
			@Override
			public double value(double[] point) {
				return val(point[0], point[1], point[2], point[3], point[4], point[5], newvals) * 1.e-10;
			}
		};

		
		final double a = 21;
		final double b = 0.0001;
		//final double[] coeffs = new Minimizer(fun).find(new double[] { a, b, 7, 0.0027, 7, 0.0027 });
		final double[] coeffs=run3d();
		@SuppressWarnings("unused")
		final IFunction reg = new Function() {
			@Override
			public double value(double[] input) {
				return coeffs[0] + coeffs[1] * input[0] + coeffs[2] * Math.sin(coeffs[3] * input[0])
						+ coeffs[4] * Math.cos(coeffs[5] * input[0]);
			}
		};

		final double correlation = correlation(values[0], values[1]);
		printDataToXml(coeffs, newvals, reg, correlation);
		System.out.println("Regression: f(x) = " + coeffs[0] + " + ( " + coeffs[1] + " ) * x + " + coeffs[2]
				+ " * sin ( " + coeffs[3] + " * x )");
		System.out.println("     + " + coeffs[4] + " * cos ( " + coeffs[5] + " * x )");
//		System.out.println(
//				"Wert: " + val(a, b, 5.952057839617729, 2.494323292555835, 0.0026746014893392765, 0, 0, 0, newvals));
//
//		System.out.println("");
//		System.out.println("Wert: "
//				+ val(coeffs[0], coeffs[1], coeffs[2], coeffs[3], coeffs[4], coeffs[5], coeffs[6], coeffs[7], newvals));
//		System.out.println("Korrelationskoeffizient = " + correlation);

		// final double[] out3d = run3d();
//		System.out.println(
//				"Brute Force: " + out3d[0] + ", " + out3d[1] + ", " + out3d[2] + ", " + out3d[3] + ", " + out3d[4]);
	}

	private static double[] run3d() {
		double min = Double.MAX_VALUE;
		double a0 = 0;
		double b0 = 0;
		double c0 = 0;
		double d0 = 0;
		double e0 = 0;
		double f0 = 0;
//		for (double a = 21.87954; a < 21.87955; a+=0.000001) {
//			for (double b = -0.00001; b < 0; b += 0.000002) {
//				for (double c = 5.815; c < 5.816; c += 0.0001) {
//					for (double d = 2.462594; d < 2.462595; d += 0.0000001) {
//						for (double e = 0.002632; e < 0.002633; e += 0.0000001) {
//							final double val = val(a, b, c, d, e, newvals);
//							if (val < min) {
//								a0 = a;
//								b0 = b;
//								c0 = c;
//								d0 = d;
//								e0 = e;
//								min = val;
//							}
//							// System.out.println(a+", "+b+", "+c+" -value="+val);
//						}
//					}
//				}
//			}
//		}
		for (double a = 15; a < 25; a += 0.1) {
			System.out.println("a=" + a);
			for (double b = -1; b < 1; b += 0.1) {
				for (double c = 1; c < 10; c += 0.5) {
					for (double d = 0; d < 0.01; d += 0.0001) {
						for (double e = 1; e < 10; e += 0.5) {
							for (double f = 0;f<0.01; f += 0.0001) {
								final double val = val(a, b, c, d, e, f, newvals);
								if (val < min) {
									a0 = a;
									b0 = b;
									c0 = c;
									d0 = d;
									e0 = e;
									f0 = f;
									min = val;
								}
							}
							// System.out.println(a+", "+b+", "+c+" -value="+val);
						}
					}
				}
			}
		}
		System.out.println(a0+" , "+b0+", "+c0+", "+d0+", "+e0);
		System.out.println(val(a0, b0, c0, d0, e0, newvals));
		return new double[] { a0, b0, c0, d0, e0 ,f0};
	}

	@SuppressWarnings("unused")
	private static void printDataToXml(double[] coeffs, double[][] newvals2, IFunction fun, double correlation)
			throws IOException {
		final double length = (int) (newvals2[0][newvals2[0].length - 1]);
		final DataSet[] data = new DataSet[(int) length];
		for (int i = 0; i < newvals2[0].length - 1; i++) {
			data[(int) newvals2[0][i]] = new DataSet((int) newvals2[0][i], newvals2[1][i], fun);
			for (int j = (int) newvals2[0][i] + 1; j < newvals2[0][i + 1]; j++) {
				data[j] = new DataSet(j, null, fun);
			}
		}

		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i].toString());
		}

		IRegression.preparePlot(values, 1000, 500);
		IRegression.drawInput(values);
		final double prec = 10;
		StdDraw.setPenRadius(0.01);
		for (double i = 0; i < values[0][values[0].length - 1]; i += prec) {
			StdDraw.setPenColor(Color.red);
			StdDraw.line(i, fun.value(new double[] { i }), i + prec, fun.value(new double[] { i + prec }));
		}
	}

	private static double correlation(double[] ds, double[] ds2) {
		final IVector mathob = new MathOp(1.e-5);
		return mathob.ScalarProduct(ds, ds2) / (mathob.MagnitudeOfVector(ds) * mathob.MagnitudeOfVector(ds2));
	}

	private static double val(double a, double b, double c, double e, double f, double h, double[][] values) {
		double ans = 0.0;
		double d = 0;
		double g = 0;
		for (int i = 0; i < values[0].length; i++) {
			ans += Math.pow(values[1][i] - a - b * values[0][i] - c * Math.sin(d + e * values[0][i])
					- f * Math.cos(g + h * values[0][i]), 2);
		}
		return ans;
	}

	private static double val(double a, double b, double c, double d, double e, double[][] values) {
		double ans = 0.0;
		for (int i = 0; i < values[0].length; i++) {
			ans += Math.pow(values[1][i] - a - b * values[0][i] - c * Math.sin(d + e * values[0][i]), 2);
		}
		return ans;
	}

	public static double[][] centralize(double[][] vals) {
		final IRegression linreg = new LinReg(vals, 1);
		final double[][] newvalues = new double[2][vals[0].length];
		newvalues[0] = vals[0];
		for (int i = 0; i < vals[0].length; i++) {
			newvalues[1][i] = vals[1][i] - linreg.getPolynomial().eval(vals[0][i]);
		}
		return newvalues;
	}
}

package br.com.dms.model.linear;

import br.com.dms.model.determinant.Determinante;
import br.com.dms.model.determinant.Utilidades;

public class Cramer {
	public static double[] calc2x2(double mat[][]) {
		double result[] = null;
		double detX[][] = new double[2][2];
		
		double detY[][] = new double[2][2];
		double detTotal[][] = new double[2][2];
		System.out.println(detX);
		System.out.println(detY);
		detX[0][0] = mat[0][2];
		detX[1][0] = mat[1][2];
		detX[0][1] = mat[0][1];
		detX[1][1] = mat[1][1];
		detY[0][0] = mat[0][0];
		detY[0][1] = mat[0][2];
		detY[1][0] = mat[1][0];
		detY[1][1] = mat[1][2];
		detTotal[0][0] = mat[0][0];
		detTotal[0][1] = mat[0][1];
		detTotal[1][0] = mat[1][0];
		detTotal[1][1] = mat[1][1];
		double detA = Determinante.calc2x2(detTotal);
		double varX = Determinante.calc2x2(detX) / detA;
		double varY = Determinante.calc2x2(detY) / detA;
		result = new double[2];
		result[0] = varX;
		result[1] = varY;
		for (int i = 0; i < result.length; i++)
			result[i] = Utilidades.detZero(result[i]);

		return result;
	}

	public static double[] calc3x3(double mat[][]) {
		double result[] = null;
		double detX[][] = new double[3][3];
		double detY[][] = new double[3][3];
		double detZ[][] = new double[3][3];
		double detTotal[][] = new double[3][3];
		detX[0][0] = mat[0][3];
		detX[0][1] = mat[1][3];
		detX[0][2] = mat[2][3];
		detX[1][0] = mat[1][0];
		detX[1][1] = mat[1][1];
		detX[1][2] = mat[1][2];
		detX[2][0] = mat[2][0];
		detX[2][1] = mat[2][1];
		detX[2][2] = mat[2][2];
		detY[0][0] = mat[0][0];
		detY[0][1] = mat[1][0];
		detY[0][2] = mat[2][0];
		detY[1][0] = mat[0][3];
		detY[1][1] = mat[1][3];
		detY[1][2] = mat[2][3];
		detY[2][0] = mat[0][2];
		detY[2][1] = mat[1][2];
		detY[2][2] = mat[2][2];
		detZ[0][0] = mat[0][0];
		detZ[0][1] = mat[1][0];
		detZ[0][1] = mat[2][0];
		detZ[1][0] = mat[0][1];
		detZ[1][1] = mat[1][1];
		detZ[1][1] = mat[2][1];
		detZ[2][0] = mat[0][3];
		detZ[2][1] = mat[1][3];
		detZ[2][1] = mat[2][3];
		detTotal[0][0] = mat[0][0];
		detTotal[0][1] = mat[1][0];
		detTotal[0][2] = mat[2][1];
		detTotal[1][0] = mat[0][1];
		detTotal[1][1] = mat[1][1];
		detTotal[1][2] = mat[2][1];
		detTotal[2][0] = mat[0][2];
		detTotal[2][1] = mat[1][2];
		detTotal[2][2] = mat[2][2];
		double detA = Determinante.calcSarrus(detTotal, 1.0F);
		double varX = Determinante.calcSarrus(detX, 1.0F) / detA;
		double varY = Determinante.calcSarrus(detY, 1.0F) / detA;
		double varZ = Determinante.calcSarrus(detZ, 1.0F) / detA;
		result = new double[3];
		result[0] = varX;
		result[1] = varY;
		result[2] = varZ;
		for (int i = 0; i < result.length; i++)
			result[i] = Utilidades.detZero(result[i]);

		return result;
	}
}

package br.com.dms.model.determinant;

public class Determinante {

	public static double calc2x2(double matriz[][]) {
		double det = 0.0F;
		double diagonalP = matriz[0][0] * matriz[1][1];
		double diagonalS = matriz[0][1] * matriz[1][0];
		det = diagonalP - diagonalS;
		det = Utilidades.detZero(det);
		return det;
	}

	public static double calcSarrus(double matriz[][], double divisor) {
		double det = 0.0F;
		double multPrincipal = matriz[0][0] * matriz[1][1] * matriz[2][2] + matriz[0][1] * matriz[1][2] * matriz[2][0]
				+ matriz[0][2] * matriz[1][0] * matriz[2][1];
		double multSecundaria = -(matriz[0][2] * matriz[1][1] * matriz[2][0])
				- matriz[0][0] * matriz[1][2] * matriz[2][1] - matriz[0][1] * matriz[1][0] * matriz[2][2];
		det = multPrincipal + multSecundaria;
		det = Utilidades.detZero(det);
		return det;
	}
}